package nl.harryt.restful.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.harryt.restful.exception.RestfulException;
import nl.harryt.restful.persistance.NameDAO;
import nl.harryt.restful.presentation.NameHtmlTablePresenter;

/**
 * Root resource (exposed at "names" path)
 */
@Path("names")
public class Names {

  Logger logger = LoggerFactory.getLogger(Names.class);

  /**
   * @return HTML table with all stored names
   */
  @GET
  @Produces(MediaType.TEXT_HTML)
  public String get() {
    String result;

    try {
      final List<String> storedNames = NameDAO.getNames();

      // Create table presentation
      result = NameHtmlTablePresenter.present(storedNames);

    } catch (final RestfulException e) {
      // Return received error message
      result = e.getMessage();

      logger.info("Could not get names", e);
    }

    return result;
  }

  /**
   * Store the name
   *
   * @param name the name to store
   * @return HTML table with all stored names on succes. <br/>
   *         On failure it'll show an error message
   */
  @POST
  @Consumes("application/x-www-form-urlencoded")
  @Produces(MediaType.TEXT_HTML)
  public String post(@FormParam("name") final String name) {
    String result;

    try {
      // Store the name
      final List<String> storedNames = NameDAO.persist(name);

      // Create table presentation
      result = NameHtmlTablePresenter.present(storedNames);

    } catch (final RestfulException e) {
      // Return received error message
      result = e.getMessage();

      logger.info("Could not add name", e);
    }

    return result;
  }

}
