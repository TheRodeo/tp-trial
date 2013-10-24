package rodeo.test.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;

@Controller
@Secured("ROLE_USER")
public class TestController {

	// private static final Logger LOG =
	// LoggerFactory.getLogger(TestController.class);
	//
	// @RequestMapping(value = "testing", method = RequestMethod.GET)
	// @ResponseStatus(value = HttpStatus.OK)
	// @ResponseBody
	// public List<Discipline> accounts(final UserDetails userDetails) throws
	// SpidyException {
	// final String response = SpidyWrapper.getDisciplines(SpidyFormat.JSON);
	// final Gson gson = new Gson();
	// final SpidyDisciplineResult spidyResult = gson.fromJson(response,
	// SpidyDisciplineResult.class);
	// final List<Discipline> disciplines =
	// ConvertSpidyResultToSpecific.convertToDiscipline(spidyResult);
	// return disciplines;
	// }
}
