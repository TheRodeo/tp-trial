package rodeo.discipline.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rodeo.base.dao.pojos.Discipline;
import rodeo.base.dao.service.BaseInformationRepository;
import de.chemist.gw2.SpidyException;
import de.chemist.gw2.SpidyWrapper;

@Service
public class DisciplineServiceImpl {

	@Autowired
	private BaseInformationRepository baseInformationRepository;

	public Discipline loadDisciplineByName(final String name) {
		final Discipline discipline = baseInformationRepository.findDisciplineByName(name);
		return discipline;
	}

	public List<Discipline> updateDisciplines() throws SpidyException {
		final List<Discipline> disciplines = SpidyWrapper.getDisciplines();
		final List<String> disciplinesFromDb = baseInformationRepository.getAllDisciplineNames();
		final List<Object> disciplinesToSave = new ArrayList<Object>();
		for (final Discipline discipline : disciplines) {
			final boolean inDb = disciplinesFromDb.contains(discipline.getName());
			if (!inDb) {
				disciplinesToSave.add(discipline);
			}
		}
		if (!disciplinesToSave.isEmpty()) {
			baseInformationRepository.saveMany(disciplinesToSave);
		}
		return disciplines;
	}
}
