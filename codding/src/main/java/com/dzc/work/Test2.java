package com.dzc.work;

import fj.Hash;

import java.io.*;
import java.util.HashSet;
import java.util.Map;

/**
 * @author Administrator
 * @date 2020-05-27 14:19
 */
public class Test2 {

    public static void main(String[] args) throws IOException {

        String text2 = "809203\t客户密码重置受理\n" +
                "809211\t客户密码重置提交\n" +
                "809204\t账户密码变更\n" +
                "809205\t电子协议签署\n" +
                "809206\t委托方式变更\n" +
                "809202\t客户交易佣金设置\n" +
                "809209\t委托方式开通\n" +
                "809210\t委托方式取消\n" +
                "809212\t资产账户开户\n" +
                "809213\t资产账户销户\n" +
                "809314\t沪港通开户\n" +
                "809215\t个人联合开户\n" +
                "809218\t机构预约开户\n" +
                "809221\t个人预约开户\n" +
                "809223\t产品预约开户\n" +
                "809354\t私募基金特定对象设置\n" +
                "809232\t非现场预销户受理\n" +
                "809234\t非现场销户挽留处理\n" +
                "809235\t账户费用设置\n" +
                "809236\t成本价类型设置\n" +
                "809237\t资产账户主账设置\n" +
                "809064\t指定受理单审核\n" +
                "809016\t业务流程受理\n" +
                "809020\t受理单作废\n" +
                "809035\t受理单关联\n" +
                "809039\t受理单办理终止\n" +
                "809072\t通用业务受理\n" +
                "809602\t三方存管开户\n" +
                "809603\t三方存管变更\n" +
                "809605\t三方存管销户\n" +
                "809607\t三方存管变更撤单\n" +
                "809610\t存管账户信息修改\n" +
                "809611\t单客户多存管销户\n" +
                "809612\t单客户多存管开户\n" +
                "809100\t客户关键信息修改\n" +
                "809107\t身份证信息变更\n" +
                "809101\t客户资料修改\n" +
                "809102\t客户风险等级设置\n" +
                "809103\t中登身份核查\n" +
                "809104\t客户权限开通\n" +
                "809105\t客户权限取消\n" +
                "809301\t创业板转签\n" +
                "809132\t客户资料修改预约\n" +
                "809134\t非居民账户信息修改\n" +
                "809400\t融资融券授信额度变更\n" +
                "809401\t信用合同中止\n" +
                "809402\t客户信用评级\n" +
                "809124\t征信问卷提交\n" +
                "809404\t客户预征信提交\n" +
                "809408\t融资融券预约开户受理提交\n" +
                "809409\t两融预约申报信息提交\n" +
                "809400\t授信额度变更提交\n" +
                "809411\t随日融协议签署\n" +
                "809412\t随日融协议注销\n" +
                "809450\t两融合约展期\n" +
                "809521\t产品风险揭示_周边\n" +
                "809522\t基金账户销户\n" +
                "809702\t自动行权协议签署\n" +
                "809703\t自动行权协议修改\n" +
                "809704\t自动行权协议注销\n" +
                "809705\t买入额度变更\n" +
                "809706\t深圳期权合约账户开户\n" +
                "809708\t深圳期权买入额度测算\n" +
                "809710\t产品账户开户\n" +
                "809711\t产品账户销户\n" +
                "809750\t产品认购\n" +
                "809751\t产品申购\n" +
                "809752\t产品赎回\n" +
                "809753\t产品业务撤单\n" +
                "809754\t产品预约认购\n" +
                "809755\t产品预约申购\n" +
                "809756\t产品预约赎回\n" +
                "809757\t产品预约撤单\n" +
                "809758\t客户确认下单\n" +
                "809305\t休眠账户激活\n" +
                "809306\t证券账户开户\n" +
                "809307\t证券账户指定\n" +
                "809308\t证券账户销户\n" +
                "809309\t证券转托管\n" +
                "809311\t证券账户关联关系维护\n" +
                "809316\t退市整理协议签署\n" +
                "809317\t风险警示协议签署\n" +
                "809319\t沪港通开户\n" +
                "809321\t深港通开户\n" +
                "809336\t一码通账户注销\n" +
                "809339\t休眠户激活\n" +
                "809344\t报价回购协议签署\n" +
                "809345\t存托凭证权限开通\n" +
                "809346\t创新企业股票权限开通\n" +
                "809349\t沪伦通权限开通\n" +
                "809350\t沪伦通权限取消\n" +
                "809351\t证券成本价设置\n" +
                "809356\t老三板权限开通\n" +
                "809359\t科创板权限开通\n" +
                "809360\t科创板权限取消\n" +
                "809367\t募合格投资者登记\n" +
                "809368\t管合格投资者登记\n" +
                "809369\t证券账户主账指定\n" +
                "809812\t深圳特定债券权限开通\n" +
                "809813\t深圳特定债券权限取消\n" +
                "809814\t上海特定债券协议签署\n" +
                "809815\t上海特定债券协议注销\n" +
                "809816\t科创板CDR权限开通\n" +
                "809817\t科创板CDR权限取消\n" +
                "809818\t股票风险警示协议注销_周边\n" +
                "809819\t股票退市整理协议注销\n" +
                "809820\t报价回购协议注销\n" +
                "809821\t私募合格投资者取消\n" +
                "809822\t资管合格投资者取消\n" +
                "809371\t上海A股指定交易\n" +
                "809372\t上海A股撤指定交易\n" +
                "809373\t港股通开户\n" +
                "809374\t深港通销户\n" +
                "809375\t沪港通销户\n" +
                "809824\t老三板权限取消\n" +
                "809825\t存托凭证权限取消\n" +
                "809826\t创新企业股票权限取消\n" +
                "809827\t上海特定债券出让协议签署\n" +
                "809828\t上海特定债券出让协议注销\n" +
                "809829\t深圳特定债券出让协议签署\n" +
                "809830\t深圳特定债券出让协议注销\n" +
                "809832\t股转合格投资者登记_周边\n" +
                "809833\t股转合格投资者注销_周边\n" +
                "809834\t股转优先股投资者登记\n" +
                "809835\t股转优先股投资者注销_周边\n" +
                "809841\t创业板注册制权限开通_周边\n" +
                "809842\t创业板注册制权限取消_周边\n" +
                "809844\t股转受限投资者登记_周边\n" +
                "809845\t股转合格投资者变更_周边\n" +
                "809301\t创业板转签\n" +
                "809302\t创业板权限开通\n" +
                "809303\t极速交易权限开通\n" +
                "809304\t新三板协转权限开通\n" +
                "809310\t股票质押权限开通\n" +
                "809312\t约定购回权限开通\n" +
                "809313\t报价回购权限开通\n" +
                "809315\t小额贷权限开通\n" +
                "809341\t新三板预约开户受理\n" +
                "809343\t股票期权预约开户\n";

        HashSet<String> key = new HashSet<>();
        String[] split = text2.split("\n");
        for (String s : split) {
            key.add(s.split("\t")[0]);
        }
        System.out.println(key);
        String text = "809203\t客户密码重置受理\n" +
                "809211\t客户密码重置提交\n" +
                "809204\t账户密码变更\n" +
                "809205\t电子协议签署\n" +
                "809206\t委托方式变更\n" +
                "809202\t客户交易佣金设置\n" +
                "809200\t客户登录\n" +
                "809209\t委托方式开通\n" +
                "809210\t委托方式取消\n" +
                "809212\t资产账户开户\n" +
                "809213\t资产账户销户\n" +
                "809314\t沪港通开户\n" +
                "809215\t个人联合开户\n" +
                "809218\t机构预约开户\n" +
                "809220\t回访激活\n" +
                "809221\t个人预约开户\n" +
                "809223\t产品预约开户\n" +
                "809225\t第三方机构核查\n" +
                "809227\t本地合伙人信息\n" +
                "809230\t非现场销户受理\n" +
                "809354\t私募基金特定对象设置\n" +
                "809232\t非现场预销户受理\n" +
                "809234\t非现场销户挽留处理\n" +
                "809235\t账户费用设置\n" +
                "809236\t成本价类型设置\n" +
                "809237\t资产账户主账设置\n" +
                "809064\t指定受理单审核\n" +
                "809011\tPDF上传\n" +
                "809013\t客户档案上传\n" +
                "809015\t客户档案上传(中\n" +
                "809016\t业务流程受理\n" +
                "809019\tpdf盖章\n" +
                "809020\t受理单作废\n" +
                "809033\t质检视频地址新增\n" +
                "809034\t影像储存记录新增\n" +
                "809035\t受理单关联\n" +
                "809039\t受理单办理终止\n" +
                "809041\t档案图片下载\n" +
                "809042\t受理认证标识更新\n" +
                "809045\t电子协议获取（预览）\n" +
                "809046\t手机号码核查\n" +
                "809049\t中登本地字典转换\n" +
                "809072\t通用业务受理\n" +
                "809073\t人脸比对(对接易道博\n" +
                "809074\t文本转语音\n" +
                "809075\t语音识别\n" +
                "809076\t人脸检测\n" +
                "809079\t受理单无纸化字段新增\n" +
                "809099\t获取错误代码对照\n" +
                "809025\t邮件验证码发送\n" +
                "809026\t邮件验证码校验\n" +
                "809050\t数字证书登记\n" +
                "809059\t数字证书申请\n" +
                "809060\t数字证书初始化\n" +
                "809061\t证书短信验证码发送\n" +
                "809053\t电子协议签署\n" +
                "809055\t电子协议协议下载\n" +
                "809057\t协同签名-步骤1：电子协议签名请求\n" +
                "809058\t协同签名-步骤2：电子协议合成请求\n" +
                "809062\t电子协议模版配置同步\n" +
                "809068\t电子协议模版下载\n" +
                "809071\t电子问卷转换PDF文件\n" +
                "809021\t短信验证码发送\n" +
                "809022\t手机验证码校验\n" +
                "809018\t信息发送信息增加\n" +
                "809023\t新增视频见证排队记录\n" +
                "809024\t取消视频见证排队\n" +
                "809048\t开户双录留痕\n" +
                "809070\t周边业务双录留痕\n" +
                "809078\t单向视频话术留痕\n" +
                "809602\t三方存管开户\n" +
                "809603\t三方存管变更\n" +
                "809605\t三方存管销户\n" +
                "809607\t三方存管变更撤单\n" +
                "809610\t存管账户信息修改\n" +
                "809611\t单客户多存管销户\n" +
                "809612\t单客户多存管开户\n" +
                "809100\t客户关键信息修改\n" +
                "809106\t客户风险能力测评\n" +
                "809107\t身份证信息变更\n" +
                "809101\t客户资料修改\n" +
                "809102\t客户风险等级设置\n" +
                "809103\t中登身份核查\n" +
                "809104\t客户权限开通\n" +
                "809105\t客户权限取消\n" +
                "809108\t客户信息校验\n" +
                "809301\t创业板转签\n" +
                "809121\t客户风险答卷提交\n" +
                "809129\t风险测评结果计算\n" +
                "809130\t客户风险测评\n" +
                "809131\t中登机构核查\n" +
                "809132\t客户资料修改预约\n" +
                "809134\t非居民账户信息修改\n" +
                "809400\t融资融券授信额度变更\n" +
                "809401\t信用合同中止\n" +
                "809402\t客户信用评级\n" +
                "809123\t征信指标获取\n" +
                "809124\t征信问卷提交\n" +
                "809404\t客户预征信提交\n" +
                "809408\t融资融券预约开户受理提交\n" +
                "809409\t两融预约申报信息提交\n" +
                "809400\t授信额度变更提交\n" +
                "809411\t随日融协议签署\n" +
                "809412\t随日融协议注销\n" +
                "809413\t授信额度测算\n" +
                "809450\t两融合约展期\n" +
                "809800\t客户业务准入条件校验\n" +
                "809802\t客户协议确认书签署功能\n" +
                "809803\t客户协议确认书下载\n" +
                "809837\t在线投教双录\n" +
                "809823\t客户四要素预校验_周边\n" +
                "809836\t受理单号回写资质审查受理表\n" +
                "809804\t短信发送\n" +
                "809806\t数字证书用户创建\n" +
                "809807\t数字证书协议签署\n" +
                "809600\t预留资金资金申请\n" +
                "809601\t预留资金资金取消\n" +
                "809502\t基金定时定定额登记\n" +
                "809503\t基金定时定额修改\n" +
                "809504\t基金定时定额取消\n" +
                "809505\t基金认申购预约\n" +
                "809506\t基金认申购\n" +
                "809509\t基金委托撤单\n" +
                "809511\t基金现金现财产品修改\n" +
                "809512\t基金现金现财产品取消\n" +
                "809513\t基金现金理财产品登记及改签\n" +
                "809508\t基金赎回\n" +
                "809507\t基金赎回预约\n" +
                "809517\t基金账户开户\n" +
                "809521\t产品风险揭示_周边\n" +
                "809522\t基金账户销户\n" +
                "809702\t自动行权协议签署\n" +
                "809703\t自动行权协议修改\n" +
                "809704\t自动行权协议注销\n" +
                "809705\t买入额度变更\n" +
                "809706\t深圳期权合约账户开户\n" +
                "809708\t深圳期权买入额度测算\n" +
                "809710\t产品账户开户\n" +
                "809711\t产品账户销户\n" +
                "809750\t产品认购\n" +
                "809751\t产品申购\n" +
                "809752\t产品赎回\n" +
                "809753\t产品业务撤单\n" +
                "809754\t产品预约认购\n" +
                "809755\t产品预约申购\n" +
                "809756\t产品预约赎回\n" +
                "809757\t产品预约撤单\n" +
                "809758\t客户确认下单\n" +
                "809305\t休眠账户激活\n" +
                "809306\t证券账户开户\n" +
                "809307\t证券账户指定\n" +
                "809308\t证券账户销户\n" +
                "809309\t证券转托管\n" +
                "809311\t证券账户关联关系维护\n" +
                "809316\t退市整理协议签署\n" +
                "809317\t风险警示协议签署\n" +
                "809319\t沪港通开户\n" +
                "809321\t深港通开户\n" +
                "809332\t中登股东账户信息应答获取\n" +
                "809336\t一码通账户注销\n" +
                "809339\t休眠户激活\n" +
                "809344\t报价回购协议签署\n" +
                "809345\t存托凭证权限开通\n" +
                "809346\t创新企业股票权限开通\n" +
                "809349\t沪伦通权限开通\n" +
                "809350\t沪伦通权限取消\n" +
                "809351\t证券成本价设置\n" +
                "809353\t中登合伙人信息应答获取\n" +
                "809356\t老三板权限开通\n" +
                "809358\t账户首笔交易日期应答获取\n" +
                "809359\t科创板权限开通\n" +
                "809360\t科创板权限取消\n" +
                "809362\t交割对账单PDF下载\n" +
                "809366\t券基金账户对应关系维护\n" +
                "809367\t募合格投资者登记\n" +
                "809368\t管合格投资者登记\n" +
                "809369\t证券账户主账指定\n" +
                "809812\t深圳特定债券权限开通\n" +
                "809813\t深圳特定债券权限取消\n" +
                "809814\t上海特定债券协议签署\n" +
                "809815\t上海特定债券协议注销\n" +
                "809816\t科创板CDR权限开通\n" +
                "809817\t科创板CDR权限取消\n" +
                "809818\t股票风险警示协议注销_周边\n" +
                "809819\t股票退市整理协议注销\n" +
                "809820\t报价回购协议注销\n" +
                "809821\t私募合格投资者取消\n" +
                "809822\t资管合格投资者取消\n" +
                "809371\t上海A股指定交易\n" +
                "809372\t上海A股撤指定交易\n" +
                "809373\t港股通开户\n" +
                "809374\t深港通销户\n" +
                "809375\t沪港通销户\n" +
                "809824\t老三板权限取消\n" +
                "809825\t存托凭证权限取消\n" +
                "809826\t创新企业股票权限取消\n" +
                "809827\t上海特定债券出让协议签署\n" +
                "809828\t上海特定债券出让协议注销\n" +
                "809829\t深圳特定债券出让协议签署\n" +
                "809830\t深圳特定债券出让协议注销\n" +
                "809831\t投顾策略适当性校验\n" +
                "809832\t股转合格投资者登记_周边\n" +
                "809833\t股转合格投资者注销_周边\n" +
                "809834\t股转优先股投资者登记\n" +
                "809835\t股转优先股投资者注销_周边\n" +
                "809841\t创业板注册制权限开通_周边\n" +
                "809842\t创业板注册制权限取消_周边\n" +
                "809844\t股转受限投资者登记_周边\n" +
                "809845\t股转合格投资者变更_周边\n" +
                "809301\t创业板转签\n" +
                "809302\t创业板权限开通\n" +
                "809303\t极速交易权限开通\n" +
                "809304\t新三板协转权限开通\n" +
                "809310\t股票质押权限开通\n" +
                "809312\t约定购回权限开通\n" +
                "809313\t报价回购权限开通\n" +
                "809315\t小额贷权限开通\n" +
                "809341\t新三板预约开户受理\n" +
                "809343\t股票期权预约开户\n";
        BufferedWriter cbw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("./function4.txt")));

        StringBuilder sb = new StringBuilder();
        for (String s : text.split("\n")) {
            if (!key.contains(s.split("\t")[0])) {
                cbw.write(s);
                cbw.newLine();
            }
        }

        cbw.close();

    }
}
