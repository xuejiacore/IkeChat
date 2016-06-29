/**
 * Project: IkeChat
 * Package Name: org.ike.wechat.core.material
 * Author: Xuejia
 * Date Time: 2016/6/26 22:49
 * Copyright: 2016 www.zigui.site. All rights reserved.
 **/
package org.ike.wechat.core.material;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import eu.medsea.mimeutil.MimeUtil;
import org.ike.wechat.core.AbstractApi;
import org.ike.wechat.core.IkeChat;
import org.ike.wechat.core.config.DefaultConfiguration;
import org.ike.wechat.core.material.bean.Article;
import org.ike.wechat.core.material.bean.Articles;
import org.ike.wechat.exception.ChatException;
import org.ike.wechat.exception.UnverifiedParameterException;
import org.ike.wechat.parser.*;
import org.ike.wechat.utils.HttpFileUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Class Name: MaterialAPI
 * Create Date: 2016/6/26 22:49
 * Creator: Xuejia
 * Version: v1.0
 * Updater:
 * Date Time:
 * Description:
 * 上传的临时多媒体文件有格式和大小限制，如下：
 * 图片（image）: 1M，支持JPG格式
 * 语音（voice）：2M，播放长度不超过60s，支持AMR\MP3格式
 * 视频（video）：10MB，支持MP4格式
 * 缩略图（thumb）：64KB，支持JPG格式
 * 媒体文件在后台保存时间为3天，即3天后media_id失效
 */
public class MaterialAPI extends AbstractApi {

    private static final String CGI_UPLOAD_TEMP_MATERIAL = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=%s&type=%s";        // 上传临时素材
    private static final String CGI_OBTAIN_TEMP_MATERIAL = "https://api.weixin.qq.com/cgi-bin/media/get?access_token=%s&media_id=%s";       // 获得临时素材列表
    private static final String CGI_CREATE_LIMIT_ARTICLES = "https://api.weixin.qq.com/cgi-bin/material/add_news?access_token=%s";          // 上传永久图文
    private static final String CGI_UPLOAD_LIMIT_MATERIAL = "https://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token=%s";            // 上传永久素材
    private static final String CGI_UPLOAD_LIMIT_OTHER_MATERIAL = "https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=%s&type=%s";    // 上传其他的永久素材
    private static final String CGI_OBTAIN_LIMIT_MATERIAL = "https://api.weixin.qq.com/cgi-bin/material/get_material?access_token=%s";      // 获得永久素材ID
    private static final String CGI_DELETE_LIMIT_MATERIAL = "https://api.weixin.qq.com/cgi-bin/material/del_material?access_token=%s";      // 删除永久素材
    private static final String CGI_MODIFY_LIMIT_MATERIAL = "https://api.weixin.qq.com/cgi-bin/material/update_news?access_token=%s";       // 修改永久图文素材
    private static final String CGI_CALCULATE_MATERIAL_CNT = "https://api.weixin.qq.com/cgi-bin/material/get_materialcount";                // 获取素材总数
    private static final String CGI_QUERY_MATERIAL_LIST = "https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token=%s";   // 查询素材列表

    static {
        MimeUtil.registerMimeDetector("eu.medsea.mimeutil.detector.MagicMimeMimeDetector");
    }

    public IParameterKey[] getNecessaryParams(int apiId) {
        try {
            if (apiIs(IkeChat.API_MA_UPLOAD_TMP_MEDIA)
                    || apiIs(IkeChat.API_MA_UPLOAD_LIMIT_MATERIAL)
                    || apiIs(IkeChat.API_MA_UPLOAD_LIMIT_OTHER_MATERIAL)) {
                return new IParameterKey[]{
                        new ParameterKey("media", "form-data中媒体文件标识，有filename、filelength、content-type等信息")
                };

            } else if (apiIs(IkeChat.API_MA_OBTAIN_TMP_MATERIAL_LIST)) {
                return new IParameterKey[]{
                        new ParameterKey("media_id", "媒体文件ID"),
                        new ParameterKey("save_path", "文件的保存路径")
                };

            } else if (apiIs(IkeChat.API_MA_CREATE_LIMIT_ARTICLES) || apiIs(IkeChat.API_MA_MODIFY_LIMIT_MATERIAL)) {
                return new IParameterKey[]{
                        new ParameterKey("articles", Articles.class.getName() + "实例")
                };

            } else if (apiIs(IkeChat.API_MA_OBTAIN_LIMIT_MATERIAL) || apiIs(IkeChat.API_MA_DELETE_LIMIT_MATERIAL)) {
                return new IParameterKey[]{
                        new ParameterKey("media_id", "要操作的素材的media_id")
                };

            } else if (apiIs(IkeChat.API_MA_QUERY_MATERIAL_LIST)) {
                return new IParameterKey[]{
                        new ParameterKey("type", "素材的类型，图片（image）、视频（video）、语音 （voice）、图文（news）"),
                        new ParameterKey("offset", "从全部素材的该偏移位置开始返回，0表示从第一个素材 返回"),
                        new ParameterKey("count", "返回素材的数量，取值在1到20之间")
                };
            }
        } catch (UnverifiedParameterException e) {
            e.printStackTrace();
        }
        return new IParameterKey[0];
    }

    public IParameterKey[] getOptionalParams(int apiId) {
        return new IParameterKey[0];
    }

    public Response req(int apiId, Parameters parameters) {
        try {
            if (apiIs(IkeChat.API_MA_UPLOAD_TMP_MEDIA)) {
                File file = (File) parameters.get("media").getValue();
                return mediaUpload(apiId, String.format(CGI_UPLOAD_TEMP_MATERIAL,
                        IkeChat.getAuthorInfo().getAccessToken(), MimeUtil.getMimeTypes(file)), file, null);

            } else if (apiIs(IkeChat.API_MA_OBTAIN_TMP_MATERIAL_LIST)) {
                return new Response(apiId, HttpFileUtils.download(String.format(CGI_OBTAIN_TEMP_MATERIAL,
                        IkeChat.getAuthorInfo().getAccessToken(), parameters.get("media_id").getValue()),
                        (String) parameters.get("save_path").getValue()));

            } else if (apiIs(IkeChat.API_MA_CREATE_LIMIT_ARTICLES)) {
                return new Response(apiId, httpsJsonPostReq(String.format(CGI_CREATE_LIMIT_ARTICLES,
                        IkeChat.getAuthorInfo().getAccessToken()), new Gson().toJson(parameters.get("articles").getValue())));

            } else if (apiIs(IkeChat.API_MA_UPLOAD_LIMIT_MATERIAL)) {
                File file = (File) parameters.get("media").getValue();
                return mediaUpload(apiId, String.format(CGI_UPLOAD_LIMIT_MATERIAL,
                        IkeChat.getAuthorInfo().getAccessToken()), file, null);

            } else if (apiIs(IkeChat.API_MA_UPLOAD_LIMIT_OTHER_MATERIAL)) {
                File file = (File) parameters.get("media").getValue();
                return mediaUpload(apiId, String.format(CGI_UPLOAD_LIMIT_OTHER_MATERIAL,
                        IkeChat.getAuthorInfo().getAccessToken(), MimeUtil.getMimeTypes(file)), file, null);

            } else if (apiIs(IkeChat.API_MA_OBTAIN_LIMIT_MATERIAL)) {
                return new Response(apiId, httpsJsonPostReq(String.format(CGI_OBTAIN_LIMIT_MATERIAL,
                        IkeChat.getAuthorInfo().getAccessToken()), "{\"media_id\":\"" + parameters.get("media_id").getValue() + "\"}"));

            } else if (apiIs(IkeChat.API_MA_DELETE_LIMIT_MATERIAL)) {
                return new Response(apiId, httpsJsonPostReq(String.format(CGI_DELETE_LIMIT_MATERIAL,
                        IkeChat.getAuthorInfo().getAccessToken()), "{\"media_id\":\"" + parameters.get("media_id").getValue() + "\"}"));

            } else if (apiIs(IkeChat.API_MA_MODIFY_LIMIT_MATERIAL)) {
                return new Response(apiId, httpsJsonPostReq(String.format(CGI_MODIFY_LIMIT_MATERIAL, IkeChat.getAuthorInfo().getAccessToken()),
                        "{\"media_id\":\"" + parameters.get("media_id").getValue() + "\",\"index\":" + parameters.getOrDef("index", 0) + ",\"articles\":\"" + new Gson().toJson(parameters.get("articles")) + "\"}"));

            } else if (apiIs(IkeChat.API_MA_CALCULATE_MATERIAL_CNT)) {
                parameters.put(new ParameterKey("access_token"), new ParameterValue(IkeChat.getAuthorInfo().getAccessToken()));
                return new Response(apiId, httpsPostReq(CGI_CALCULATE_MATERIAL_CNT, parameters));

            } else if (apiIs(IkeChat.API_MA_QUERY_MATERIAL_LIST)) {
                return new Response(apiId, httpsJsonPostReq(String.format(CGI_QUERY_MATERIAL_LIST, IkeChat.getAuthorInfo().getAccessToken()),
                        "{\"type\":\"" + parameters.get("type").getValue() +
                                "\",\"offset\":" + parameters.get("offset").getValue() +
                                ",\"count\":" + parameters.get("count").getValue() + "}"));

            }
        } catch (UnverifiedParameterException e) {
            e.printStackTrace();
        } catch (ChatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 多媒体文件上传
     *
     * @param apiId 调用的API
     * @param file  需要上传的媒体文件
     * @return 返回响应结果
     * @throws IOException
     * @throws ChatException
     */
    private Response mediaUpload(int apiId, String url, File file, JsonObject jsonObject) throws IOException, ChatException {
        try {
            // 拼装请求地址
            URL uploadUrl = new URL(url);
            String result;
            long fileLength = file.length();
            String fileName = file.getName();
            HttpURLConnection con = (HttpURLConnection) uploadUrl.openConnection();
            con.setRequestMethod("POST"); // 以Post方式提交表单，默认get方式
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setUseCaches(false); // post方式不能使用缓存
            // 设置请求头信息
            con.setRequestProperty("Connection", "Keep-Alive");
            con.setRequestProperty("Charset", "UTF-8");

            // 设置边界,这里的boundary是http协议里面的分割符，不懂的可惜百度(http 协议 boundary)，这里boundary 可以是任意的值(111,2222)都行
            String BOUNDARY = "----------" + System.currentTimeMillis();
            con.setRequestProperty("Content-Type",
                    "multipart/form-data; boundary=" + BOUNDARY);
            StringBuilder sb = new StringBuilder();
            //这块是post提交type的值也就是文件对应的mime类型值
            sb.append("--");
            sb.append(BOUNDARY);
            sb.append("\r\n");
            sb.append("Content-Disposition: form-data;name=\"type\" \r\n\r\n"); //这里是参数名，参数名和值之间要用两次
            sb.append(MimeUtil.getMimeTypes(file).toString()).append("\r\n"); //参数的值

            //这块是上传video是必须的参数，你们可以在这里根据文件类型做if/else 判断
            sb.append("--");
            sb.append(BOUNDARY);
            sb.append("\r\n");
            sb.append("Content-Disposition: form-data;name=\"description\" \r\n\r\n");
            if (jsonObject != null)
                sb.append(jsonObject.toString()).append("\r\n");

            sb.append("--");
            sb.append(BOUNDARY);
            sb.append("\r\n");
            //这里是media参数相关的信息，这里是否能分开下我没有试，感兴趣的可以试试
            sb.append("Content-Disposition: form-data;name=\"media\";filename=\"").append(fileName).append("\";filelength=\"").append(fileLength).append("\" \r\n");
            sb.append("Content-Type:application/octet-stream\r\n\r\n");
            byte[] header = sb.toString().getBytes("UTF-8");
            // 获得输出流
            OutputStream out = new DataOutputStream(con.getOutputStream());
            out.write(header);
            // 文件正文部分，把文件已流文件的方式 推入到url中
            DataInputStream in = new DataInputStream(new FileInputStream(file));
            int bytesLen;
            byte[] bufferOut = new byte[1024];
            while ((bytesLen = in.read(bufferOut)) != -1) {
                out.write(bufferOut, 0, bytesLen);
            }
            in.close();
            // 结尾部分，这里结尾表示整体的参数的结尾，结尾要用"--"作为结束，这些都是http协议的规定
            byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");// 定义最后数据分隔线
            out.write(foot);
            out.flush();
            out.close();
            StringBuilder buffer = new StringBuilder();
            BufferedReader reader;
            // 定义BufferedReader输入流来读取URL的响应
            reader = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            result = buffer.toString();

            return new Response(apiId, result);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ChatException e) {
            e.printStackTrace();
        }
        return new Response(-1, null);
    }

    public static void main(String[] args) throws IOException, ChatException {
        IkeChat.loadConfiguration(new DefaultConfiguration());
//        IkeChat.req(IkeChat.API_REFRESH_TOKEN, IkeChat.PARAM_RELEASE_LOCKER);
//        System.err.println(IkeChat.req(IkeChat.API_MA_UPLOAD_TMP_MEDIA, new Object[][]{{"media", new File("f:\\demo.jpg")}}));
//        System.err.println(IkeChat.req(IkeChat.API_MA_OBTAIN_TMP_MATERIAL_LIST,
//                new Object[][]{{"media_id", "ZheZ_0DSB6fD4c3QenSZlmzCj7JpTqULGIJ6moGAumDZp7Q45hRlzSQIX9bA1IWM"},
//                        {"save_path", "f:\\down.jpg"}}).getObjectResult());
/*
        Article article = new Article();
        article.setTitle("改测试标题");
        article.setThumb_media_id("EBtBGAls6nItUkgKaKyUm5Kyg1lW95LZpo7SNwhSQL0"); // 永久图片素材
        article.setAuthor("改测试作者");
        article.setDigest("改测试摘要");
        article.setShow_cover_pic(1);
        article.setContent("<h1>html改类型的content</h1>");
        article.setContent_source_url("http://www.baidu.com");

        Articles articles = new Articles();
        articles.addArticle(article);
*/

//        System.err.println(IkeChat.req(IkeChat.API_MA_CREATE_LIMIT_ARTICLES, new Object[][]{{"articles", articles}})); // 获得新的：EBtBGAls6nItUkgKaKyUm84mxMKISvokLVenSGeXVww
//        System.err.println(IkeChat.req(IkeChat.API_MA_UPLOAD_LIMIT_MATERIAL, new Object[][]{{"media", new File("f:\\index.jpg")}}));
//        System.err.println(IkeChat.req(IkeChat.API_MA_UPLOAD_LIMIT_OTHER_MATERIAL, new Object[][]{{"media", new File("f:\\index.jpg")}}));
//        System.err.println(IkeChat.req(IkeChat.API_MA_OBTAIN_LIMIT_MATERIAL, new Object[][]{{"media_id", "EBtBGAls6nItUkgKaKyUmzH5uqq9DxeDHqFALJrxSF4"}}));
//        System.err.println(IkeChat.req(IkeChat.API_MA_DELETE_LIMIT_MATERIAL, new Object[][]{{"media_id", "EBtBGAls6nItUkgKaKyUmzH5uqq9DxeDHqFALJrxSF4"}}));
//        System.err.println(IkeChat.req(IkeChat.API_MA_MODIFY_LIMIT_MATERIAL, new Object[][]{{"articles", article},{"media_id","EBtBGAls6nItUkgKaKyUm84mxMKISvokLVenSGeXVww"}, {"index", 0}}));
//        System.err.println(IkeChat.req(IkeChat.API_MA_CALCULATE_MATERIAL_CNT, IkeChat.PARAM_EMPTY));
//        System.err.println(IkeChat.req(IkeChat.API_MA_QUERY_MATERIAL_LIST, new Object[][]{
//                {"type", MaterialType.MATERIAL_TYPE_IMAGE},
//                {"count", 20},
//                {"offset", 1}}).toClz(Item.class));
    }
}
