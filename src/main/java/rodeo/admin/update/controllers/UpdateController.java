package rodeo.admin.update.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import rodeo.base.dao.pojos.Discipline;
import rodeo.base.ws.pojos.SpidyDisciplineResult;
import rodeo.discipline.service.DisciplineServiceImpl;
import de.chemist.gw2.SpidyException;
import de.chemist.gw2.SpidyFormat;
import de.chemist.gw2.SpidyWrapper;

@Controller
@Secured("ROLE_ADMIN")
@RequestMapping(value = "update", method = RequestMethod.GET)
public class UpdateController {

	private static final Logger LOG = LoggerFactory.getLogger(UpdateController.class);

	@Autowired
	private DisciplineServiceImpl disciplineServiceImpl;

	@RequestMapping(value = "see/disciplines", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public SpidyDisciplineResult seeDisciplines(final UserDetails userDetails) throws SpidyException {
		return SpidyWrapper.getDisciplines(SpidyFormat.JSON);
	}

	@RequestMapping(value = "update/disciplines", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public List<Discipline> updateDisciplines(final UserDetails userDetails) throws SpidyException {
		return disciplineServiceImpl.updateDisciplines();
	}
}
