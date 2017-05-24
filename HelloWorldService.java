package com.patternrecognition.rest;

import java.io.File;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

@Path("recommendation")
public class HelloWorldService {

	@GET
	@Path("{userid}/{noofrec}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<OutputFormat> getMsg(@PathParam("userid") long userid, @PathParam("noofrec") int noofRec) {
		Instant starttime = Instant.now();
		String s="";
		List<OutputFormat> output=new ArrayList<OutputFormat>();
		try{
			java.nio.file.Path currentRelativePath = (java.nio.file.Path) Paths.get("");
			s = ((java.nio.file.Path) currentRelativePath).toAbsolutePath().toString();
			DataModel model = new FileDataModel(new File("C:\\Users\\cheru\\Desktop\\dataset.csv"));
	    	//DataModel model2 = new FileDataModel(new File("C:\\Users\\cheru\\Desktop\\"));
			UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
	    	UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.1, similarity, model);
	    	UserBasedRecommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);
	    	List<RecommendedItem> recommendations = recommender.recommend(userid,noofRec);
	    	for (RecommendedItem recommendation : recommendations) 
	    	{
	    		
	    	  System.out.println(recommendation);
	    	  // read movie name using reco... . getItemID()
			  // String moviename=
	    	  //long id = recommendation.getItemID();
	    	  output.add(new OutputFormat(recommendation.getItemID(),recommendation.getValue()));
	    	}
	    	Instant endtime = Instant.now();
	    	long ns = Duration.between(starttime, endtime).toNanos();
	    	System.out.println("time taken: "+ ns+ " nanoseconds");
		}catch(Exception e){
			System.out.println(s+" "+e);
		}
		
		return output;
	}
	
//	@GET
//	@Path("/test")
//	public Response getMsgtest() {
//
//		String output = "Jersey say : " ;
//
//		return Response.status(200).entity(output).build();
//
//	}

}
