package com.knf.dev.demo.springbootdatajpagcpsqlmysqlcrud;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import java.io.FileInputStream;
import java.util.UUID;

public class DataBucketUtil {

    public int generateRandomHashCode() {
        UUID uuid = UUID.randomUUID();
        long mostSigBits = uuid.getMostSignificantBits();
        long leastSigBits = uuid.getLeastSignificantBits();
        int hashCode = (int) (mostSigBits ^ (mostSigBits >>> 32)
                ^ leastSigBits ^ (leastSigBits >>> 32));
        return hashCode;
    }

    public String uploadFile(String fileContent, String extension) {

        try {

            String filename = "Main"
                    + String.valueOf(generateRandomHashCode())
                    + "." + extension;

            OkHttpClient client = new OkHttpClient().newBuilder().build();

            MediaType mediaType = MediaType.parse("text/x-java-source");
            RequestBody body = RequestBody.create(mediaType, fileContent);

            Request request = new Request.Builder()
                    .url("https://storage.googleapis.com/cloudcompileruserstorage/" + filename)
                    .method("PUT", body)
                    .addHeader("Authorization",
                            "Bearer ya29.a0AWY7Ckl8GcwNQNIfqfdCHVnBvOupjReVgoqLmBLhMgJHN6WG1vNGU8ZyfdgPYFnocxQfeXXsE2QjijhgPcYvM9gtVP5Pnrz4G6aTA5m1LIx6T1qKVmMpHwps51JMT1Va7yB__creyDhnlDVbRKvp8I1c505YJMy6vEbAMSTniTtXcKqRF9vis1bLKTNJQk4y8Qmy0f0MYBRRLQQcvCbTYhVMKSvuwL3r57CxdXHPaCgYKAXASARMSFQG1tDrpwdj2LFGXUi5zs7V9N1ri-Q0239")
                    .addHeader("Content-Type", "text/x-java-source")
                    .build();

            Response response = client.newCall(request).execute();
            System.out.println(response.body().string());
            return filename;

        } catch (Exception e) {
            System.out.println(e);

            return "phata";
        }
    }

    public String getFileData(String fileName) {

        try {

            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            Request request = new Request.Builder()
                    .url("https://www.googleapis.com/storage/v1/b/cloudcompileruserstorage/o/" + fileName
                            + "?alt=media")
                    .method("GET", null)
                    .build();
            Response response = client.newCall(request).execute();
            return String.valueOf(response.body().string());

        } catch (Exception e) {
            System.out.println(e);

            return "phata";
        }
    }

}
