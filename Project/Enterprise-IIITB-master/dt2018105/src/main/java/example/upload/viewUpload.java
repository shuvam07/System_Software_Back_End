package example.upload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
 
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

@Path("/upload")
public class viewUpload {
	
	@POST
    @Path("/image")
    @Consumes({MediaType.MULTIPART_FORM_DATA})
    public Response uploadPdfFile(  @FormDataParam("file") InputStream fileInputStream,
                                    @FormDataParam("file") FormDataContentDisposition fileMetaData) throws Exception
    {
		String UPLOAD_PATH = "C:/Users/Hario/eclipse-workspace/dt2018105/src/main/webapp/img/";
		System.out.println(fileMetaData.getFileName());
        try
        {
            int read = 0;
            byte[] bytes = new byte[1024];
            OutputStream out = new FileOutputStream(new File(UPLOAD_PATH + fileMetaData.getFileName()));
            while ((read = fileInputStream.read(bytes)) != -1)
            {
                out.write(bytes, 0, read);
            }
            out.flush();
            out.close();
            System.out.println("success");
        } catch (IOException e)
        {
        	System.out.println(e.getMessage());
            throw new WebApplicationException("Error while uploading file. Please try again !!");
        }
        
        return Response.ok(fileMetaData.getFileName() + " uploaded successfully !!").build();
    }
}
