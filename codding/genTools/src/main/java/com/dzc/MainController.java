package com.dzc;

import com.dzc.util.ProcessUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * @author Administrator
 * @date 2020-05-09 13:35
 */
@Slf4j
public class MainController implements Initializable {

    @FXML
    private TextField stdText;

    @FXML
    private TextField describeText;

    @FXML
    private TextField outputText;


    private static final String DEFAULT_PATH = System.getProperty("user.home");


    @FXML
    public void chooseStdField() {
        FileChooser fileChooser = new FileChooser();
        configureFileChooser(fileChooser, "标准字段", ProcessUtils.stdPath);
        File stdFile = fileChooser.showOpenDialog(stdText.getScene().getWindow());
        if (stdFile != null) {
            // process file
            ProcessUtils.stdPath = stdFile.getPath();
            stdText.setText(stdFile.getPath());
            try {
                ProcessUtils.parseStdField(stdFile);
            } catch (Exception e) {
                // 解析文件失败.
                log.error("解析文件失败 : {}", e.toString());
            }
        }
    }


    @FXML
    public void chooseDescribeFile() {
        FileChooser fileChooser = new FileChooser();
        configureFileChooser(fileChooser, "描述文件", ProcessUtils.describePath);
        File describe = fileChooser.showOpenDialog(stdText.getScene().getWindow());
        if (describe != null) {
            // process file
            ProcessUtils.describePath = describe.getPath();
            describeText.setText(describe.getPath());

            // excel 操作.
            ProcessUtils.readExcel(describe);
        }
    }


    @FXML
    public void chooseOutDir() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        if (StringUtils.isNotBlank(ProcessUtils.outputDir)) {
            directoryChooser.setInitialDirectory(new File(ProcessUtils.outputDir));
        }
        File dir = directoryChooser.showDialog(stdText.getScene().getWindow());
        if (dir != null) {
            ProcessUtils.outputDir = dir.getPath();
            outputText.setText(dir.getPath());
        }
    }

    @FXML
    public void generate() {
        try {
            ProcessUtils.createOutputFile(ProcessUtils.outputDir);

            // 生成文件成功 .
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.titleProperty().set("提示");
            alert.headerTextProperty().set("文件生成成功！");
            alert.showAndWait();
        } catch (Exception e) {
            log.error("生成文件失败: {}", e.toString());
        }
    }

    private void configureFileChooser(FileChooser fileChooser, String title, String path) {
        fileChooser.setTitle(title);
        if (path == null) {
            fileChooser.setInitialDirectory(new File(DEFAULT_PATH));
        } else {
            path = path.substring(0, path.lastIndexOf(File.separator));
            fileChooser.setInitialDirectory(new File(path));
        }

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter(".xlsx", "*.xlsx"),
                new FileChooser.ExtensionFilter("stdfield", "*.stdfield"),
                new FileChooser.ExtensionFilter(".xls", "*.xls")
        );
    }

    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  <tt>null</tt> if the location is not known.
     * @param resources The resources used to localize the root object, or <tt>null</tt> if
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            FileInputStream in = new FileInputStream("path.properties");
            Properties pro = new Properties();
            pro.load(in);
            ProcessUtils.stdPath = pro.getProperty("stdFile");
            ProcessUtils.describePath = pro.getProperty("describe");
            ProcessUtils.outputDir = pro.getProperty("outputPath");
        } catch (Exception e) {
            log.warn("获取配置文件失败， 没有读取到默认配置.");
        }

        stdText.setText(ProcessUtils.stdPath);
        describeText.setText(ProcessUtils.describePath);
        outputText.setText(ProcessUtils.outputDir);

        // 提前解析好标准字段文件.
        if (StringUtils.isNotBlank(ProcessUtils.stdPath)) {
            try {
                ProcessUtils.parseStdField(new File(ProcessUtils.stdPath));
            } catch (Exception e) {
                System.out.println("解析标准字段出错");
            }
        }

        if (StringUtils.isNotBlank(ProcessUtils.describePath)) {
            ProcessUtils.readExcel(new File(ProcessUtils.describePath));
        }
    }
}
