package com.dzc;

import com.dzc.util.ProcessUtils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

import java.io.FileOutputStream;
import java.util.Properties;

/**
 * @author Administrator
 * @date 2020-05-09 13:35
 */
@Slf4j
public class MainApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Main.fxml"));
        Scene scene = new Scene(root, 600, 500);
        scene.getStylesheets().add(getClass().getClassLoader().getResource("application.css").toExternalForm());

        primaryStage.setTitle("内存表生成工具");
        primaryStage.setScene(scene);
        primaryStage.show();


        primaryStage.setOnCloseRequest(event -> {
            // 保存文件.
            Properties pro = new Properties();
            if (ProcessUtils.stdPath == null || ProcessUtils.describePath == null || ProcessUtils.outputDir == null) {
                return;
            }
            pro.setProperty("stdFile", ProcessUtils.stdPath);
            pro.setProperty("describe", ProcessUtils.describePath);
            pro.setProperty("outputPath", ProcessUtils.outputDir);
            try {
                pro.store(new FileOutputStream("path.properties"), "path");
            } catch (Exception e) {
                log.error("保存文件路径失败. {}", e.toString());
            }
        });
    }
}
