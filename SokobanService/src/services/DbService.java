package services;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import data.Solution;

@Path("resources")
public class DbService {
	
	SessionFactory factory;
	Session session;
	Transaction transaction;
	
	
	@Path("get/{param}")
	@GET
	public String getSolution(@PathParam("param") int levelHash){
		openDB();
		Solution s = session.get(Solution.class, levelHash);
		closeDB();
		if(s!=null)
			return s.getSolution();
		else return "";
	}
	
	@GET
	@Path("save/{levelName}/{newSolution}")
	public Response saveOrUpdateSolution(
			@PathParam("levelName") String levelName,
			@PathParam("newSolution") String newSolution,
			@PathParam("levelHash") int levelHash)
	{
		openDB();
		Solution s = session.get(Solution.class, levelHash);
		//if this solution exists in the data base update to the minimal
		if(s != null){
			String oldSolution = s.getSolution();
			if(oldSolution.length() > newSolution.length())
				session.saveOrUpdate(new Solution(levelName,newSolution,levelHash));
		}
		else {
			session.save(new Solution(levelName,newSolution,levelHash));
		}
		closeDB();
		return Response.status(200).build(); 
	}
	
	private void openDB(){
		factory = new Configuration().configure().buildSessionFactory();
		session = factory.openSession();
		transaction = session.beginTransaction();
	}
	
	private void closeDB(){
		transaction.commit();
		session.close();
		factory.close();
	}
}
