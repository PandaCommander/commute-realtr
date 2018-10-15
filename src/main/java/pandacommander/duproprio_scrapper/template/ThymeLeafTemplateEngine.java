package pandacommander.duproprio_scrapper.template;
 
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.FileTemplateResolver;

import pandacommander.duproprio_scrapper.parsing.RichListing;
 
public class ThymeLeafTemplateEngine implements ListingTemplateEngine {
    private TemplateEngine templateEngine;
     
    public ThymeLeafTemplateEngine(){
        FileTemplateResolver templateResolver = new FileTemplateResolver();
        templateResolver.setPrefix(getTemplatePath());
        templateResolver.setTemplateMode("HTML");
        templateResolver.setCharacterEncoding("UTF-8");
        
        templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
    }
     
    private String getTemplatePath(){
        return ThymeLeafTemplateEngine.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "templates/";
    }
     
    public void processListing(List<RichListing> listings, String outputPath){
    	Context context = new Context();
        context.setVariable("listings", listings);

        try(Writer writer = new OutputStreamWriter(new FileOutputStream(outputPath), StandardCharsets.UTF_8)) {
			writer.write(templateEngine.process("ListingTemplate.html", context));
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
}