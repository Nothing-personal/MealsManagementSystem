package controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.DataService;
import utils.Ajax;
import utils.RestException;

import java.util.Map;
import java.util.Set;

/**
 * Created by Kevin Khanda on 6/19/2016.
 * Controller with two methods for saving data and for sending random data to client.
 * It has exception handler only as a template. Should be improved for future execution.
 */

@Controller
public class DataController extends ControllerExceptionHandler {

    private static final Logger log = Logger.getLogger(DataController.class);

    @Autowired
    @Qualifier("dataService")
    private DataService dataService;

    @RequestMapping(value = "/persist", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> persist(@RequestParam("data") String data) throws RestException {
        try {
            if (data == null || data.equals("")) {
                return Ajax.emptyResponse();
            }
            dataService.persist(data);
            return Ajax.emptyResponse();
        } catch (Exception e) {
            throw new RestException(e);
        }
    }

    @RequestMapping(value = "/getRandomData", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getRandomData() throws RestException {
        try {
            Set<String> result = dataService.getRandomData();
            return Ajax.successResponse(result);
        } catch (Exception e) {
            throw new RestException(e);
        }
    }

    @RequestMapping(value = "/error", method = RequestMethod.TRACE)
    public @ResponseBody
    Map<String, Object> getErrorMessage() throws RestException {
        try {
            return Ajax.errorResponse("Some kind of error");
        } catch (Exception e) {
            throw new RestException(e);
        }
    }
}