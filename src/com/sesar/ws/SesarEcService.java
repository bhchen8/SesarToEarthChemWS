package com.sesar.ws;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sesar.dao.SamplingFeatureDao;
import com.sesar.model.*;

// Plain old Java Object it does not extend as class or implements
// an interface

// The class registers its methods for the HTTP GET request using the @GET annotation.
// Using the @Produces annotation, it defines that it can deliver several MIME types,
// text, XML and HTML.

// The browser requests per default the HTML MIME type.

//Sets the path to base URL + /hello
/*

http://localhost:8080/sesarec/ssec/samples
application/xml
accept-language text/html

<?xml version="1.0" encoding="UTF-8"?>
<samples xmlns:sam2="http://app.geosamples.org">
<sam2:sample>
<sam2:sample_type>sample_type</sam2:sample_type>
<sam2:name>Jack</sam2:name>
<sam2:sample_other_names>
<sam2:sample_other_name>othername
</sam2:sample_other_name>
</sam2:sample_other_names>
<sam2:external_urls>
<sam2:external_url>
<sam2:url>url123</sam2:url>
<sam2:description>a</sam2:description>
<sam2:url_type></sam2:url_type>
</sam2:external_url>
</sam2:external_urls>
<sam2:classification>
<sam2:Rock>  
<sam2:Metamorphic>
<sam2:MetamorphicType>MetamorphicType123
</sam2:MetamorphicType>
</sam2:Metamorphic>
</sam2:Rock>
</sam2:classification>
</sam2:sample>
</samples>
*/

@Path("/ssec")
public class SesarEcService {

	@Path("/samples")
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String getSamples(Samples samples) {
		String error = null;
		String name = null;
		List<Sample> list = samples.getSamples();
		for(Sample sample: list) {
			name = sample.getName();
			error = new SamplingFeatureDao(sample).saveDataToDB();		
			if(error != null) break;
		}
		if(error != null) return "Error: "+error+" The data for sample "+name+" was not saved to the database.";
		else return "The data have been saved to database!";
	}
	
}
