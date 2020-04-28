package com.podio.file;

import com.podio.BaseAPI;
import com.podio.ResourceFactory;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileAPI extends BaseAPI {

    public FileAPI(ResourceFactory resourceFactory) {
        super(resourceFactory);
    }

    /**
     * Downloads the file and saves it to given file
     *
     * @param fileId The id of the file to download
     * @param target The target file to save the contents to
     * @throws IOException If there was an error reading or writing the file
     */
    public void downloadFile(int fileId, java.io.File target, FileSize size)
            throws IOException {
        String path = "/" + fileId;
        if (size != null) {
            path = path + "/" + size.name().toLowerCase();
        }
        var builder = getResourceFactory().getFileResource(path);
        byte[] data = builder.get(byte[].class);
        FileUtils.writeByteArrayToFile(target, data);
    }

    /**
     * Uploads the file to the API
     */
    public int uploadFile(String name, java.io.File file) {
        FileDataBodyPart filePart = new FileDataBodyPart("source", file);
        // Work around for bug in cherrypy
        FormDataContentDisposition.FormDataContentDispositionBuilder builder = FormDataContentDisposition
                .name(filePart.getName());
        builder.fileName(file.getName());
        builder.size(file.length());
        filePart.setFormDataContentDisposition(builder.build());

        FormDataMultiPart multiPart = new FormDataMultiPart();
        multiPart.bodyPart(filePart);
        multiPart.field("filename", name);

        var resource = getResourceFactory().getApiResource("/file/v2/")
                .post(Entity.entity(multiPart,
                        new MediaType("multipart", "form-data", Collections
                                .singletonMap("boundary", "AaB03x"))), File.class);
        return resource.getId();
    }

    public Integer uploadImage(URL url) throws IOException {
        return uploadImage(url, null);
    }

    public int uploadImage(URL url, String name) throws IOException {
        java.io.File file = readURL(url);
        try {
            String path = url.getPath();
            int lastSlashIdx = path.lastIndexOf('/');
            if (name == null) {
                name = path.substring(lastSlashIdx + 1);
            }

            return uploadFile(name, file);
        } finally {
            file.delete();
        }
    }

    private static java.io.File readURL(URL url) throws IOException {
        InputStream is = url.openStream();
        try {
            java.io.File file = java.io.File.createTempFile(
                    Integer.toString(url.hashCode()), null);
            file.deleteOnExit();
            FileOutputStream os = FileUtils.openOutputStream(file);
            try {
                IOUtils.copy(is, os);
            } finally {
                os.close();
            }

            return file;
        } finally {
            is.close();
        }
    }

    /**
     * Returns the file with the given id
     */
    public File getFile(int fileId) {
        return getResourceFactory().getApiResource("/file/" + fileId).get(
                File.class);
    }

    /**
     * Used to update the description of the file.
     */
    public void updateFile(int fileId, FileUpdate update) {
        getResourceFactory().getApiResource("/file/" + fileId)
                .put(Entity.entity(update, MediaType.APPLICATION_JSON_TYPE));
    }

    /**
     * Deletes the file with the given id.
     */
    public void deleteFile(int fileId) {
        getResourceFactory().getApiResource("/file/" + fileId).delete();
    }

    /**
     * Returns all the files related to the items in the application. This
     * includes files both on the item itself and in comments on the item.
     *
     * @param limit  The maximum number of files to be returned. Defaults to 50 and
     *               cannot be higher than 100.
     * @param offset The offset to use when returning files to be used for
     *               pagination. Defaults to 0 (no offset).
     */
    public List<File> getOnApp(int appId, Integer limit, Integer offset) {
        Map<String, String> queryParams = new HashMap<>();
        if (limit != null) {
            queryParams.put("limit", limit.toString());
        }
        if (offset != null) {
            queryParams.put("offset", offset.toString());
        }
        var resource = getResourceFactory().getApiResource("/file/app/" + appId + "/", queryParams);


        return resource.get(new GenericType<List<File>>() {
        });
    }

    /**
     * Returns all the files on the space order by the file name.
     *
     * @param limit  The maximum number of files to be returned. Defaults to 50 and
     *               cannot be higher than 100.
     * @param offset The offset to use when returning files to be used for
     *               pagination. Defaults to 0 (no offset).
     */
    public List<File> getOnSpace(int spaceId, Integer limit, Integer offset) {
        Map<String, String> queryParams = new HashMap<>();
        if (limit != null) {
            queryParams.put("limit", limit.toString());
        }
        if (offset != null) {
            queryParams.put("offset", offset.toString());
        }
        var resource = getResourceFactory().getApiResource("/file/space/" + spaceId + "/", queryParams);

        return resource.get(new GenericType<List<File>>() {
        });
    }
}
