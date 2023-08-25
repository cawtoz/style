package com.github.cawtoz.style.util;

import com.github.cawtoz.style.Style;
import com.github.cawtoz.style.util.file.FileManager;
import com.github.cawtoz.style.util.file.FileUtil;
import lombok.Getter;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class License {

    @Getter
    private final boolean enabled;
    private final URI host = URI.create("");
    private final String apiKey = "";
    private final String licenseKey;
    private final String product = Style.getInstance().getDescription().getName();
    private final String version = Style.getInstance().getDescription().getVersion();
    private String hwid = generateHWID();

    public License() {
        FileManager.loadFile("license.yml");
        licenseKey = FileUtil.getString("license", "LICENSE-KEY");
        enabled = check();
        ChatUtil.sendConsole(
                " ",
                "&8------------- &6" + product + " &fv" + version + " &8-------------",
                "&6 Authors&8: &f" + Style.getInstance().getDescription().getAuthors(),
                (enabled) ? "&6 Status&8: &aEnabled" :"&6 Status&8: &cDisabled",
                "&6 License&8: &f" + licenseKey,
                "&6 Support&8: &fdiscord.gg/ZzvcNqrSXA",
                "&8----------------------------------------",
                " "
        );
    }

    private boolean check() {

        HttpPost post = new HttpPost(host);

        List<NameValuePair> urlParameters = new ArrayList<>();
        urlParameters.add(new BasicNameValuePair("licensekey", licenseKey));
        urlParameters.add(new BasicNameValuePair("product", product));
        urlParameters.add(new BasicNameValuePair("version", version));
        urlParameters.add(new BasicNameValuePair("hwid", hwid));

        try {
            post.setEntity(new UrlEncodedFormEntity(urlParameters));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        post.setHeader("Authorization", apiKey);

        try (CloseableHttpClient httpClient = HttpClients.createDefault(); CloseableHttpResponse response = httpClient.execute(post)) {
            String data = EntityUtils.toString(response.getEntity());
            JSONObject obj = new JSONObject(data);

            System.out.println(data);

            if(!obj.has("status_msg") || !obj.has("status_id")) {
                return false;
            }

            if(obj.getString("status_overview") == null){
                return false;
            }

            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }



    private String generateHWID() {
        StringBuilder hwidBuilder = new StringBuilder();

        try {

            String machineName = System.getProperty("user.name");
            hwidBuilder.append(machineName);

            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = networkInterfaces.nextElement();
                byte[] macBytes = networkInterface.getHardwareAddress();
                if (macBytes != null) {
                    for (byte b : macBytes) {
                        hwidBuilder.append(String.format("%02X", b));
                    }
                }
            }

            return hwidBuilder.toString();
        } catch (SocketException e) {
            e.printStackTrace();
            return "Error generating HWID";
        }
    }

}
