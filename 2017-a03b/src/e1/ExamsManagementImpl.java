package e1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

public class ExamsManagementImpl implements ExamsManagement {
	
	private final static int GREATER_WEIGTH = 60;
	private final static int LOWER_WEIGTH = 40;
	
	private final List<Student> students = new ArrayList<>();
	private final List<LabEvaluation> labEvaluations = new ArrayList<>();
	private final List<ProjectEvaluation> projectEvaluations = new ArrayList<>();
	private final Map<Student, Integer> finalEvaluations = new HashMap<>();
	
	@Override
	public void createStudent(int studentId, String name) {
		if(this.findStudentFromId(studentId).isPresent()) {
			throw new IllegalStateException();
		}
		this.students.add(new Student(name, studentId));
	}

	private <T> Optional<T> getElementFromList(final List<T> list, Predicate<? super T> predicate) {
		return list.stream().filter(t -> predicate.test(t)).findAny();
	}
	
	private Optional<Student> findStudentFromId(final int id) {
		return this.getElementFromList(this.students, student -> student.getId() == id);
	}
	
	private Optional<ProjectEvaluation> findProjFromId(final int studentId) {
		return this.getElementFromList(this.projectEvaluations, proj -> proj.getStudent().getId() == studentId);
	}
	
	private Optional<LabEvaluation> findLabFromStudenIdAndExamId(final int studentId, final int examId) {
		return this.getElementFromList(this.labEvaluations, lab -> lab.getStudent().getId() == studentId && lab.getId() == examId);
	}
	
	@Override
	public void registerLabEvaluation(int studentId, int evaluation, int exam) {
		if(this.findLabFromStudenIdAndExamId(studentId, exam).isPresent()) {
			throw new IllegalStateException();
		}
		this.labEvaluations.add(new LabEvaluation(exam, this.findStudentFromId(studentId).orElseThrow(() -> new IllegalStateException()), evaluation));
	}

	@Override
	public void registerProjectEvaluation(int studentId, int evaluation, String project) {
		if(this.findProjFromId(studentId).isPresent()) {
			throw new IllegalStateException();
		}
		this.projectEvaluations.add(new ProjectEvaluation(project, this.findStudentFromId(studentId).orElseThrow(() -> new IllegalStateException()), evaluation));
	}
	
	@Override
	public Optional<Integer> finalEvaluation(int studentId) {
		Optional<Integer> projectEvaluation = this.projectEvaluations.stream().filter(projEv -> projEv.getStudent().getId() == studentId).map(ev -> ev.getEvaluation()).findAny();
		Optional<Integer> labEvaluation = this.labEvaluations.stream().filter(labEv -> labEv.getStudent().getId() == studentId).max((ev1, ev2) -> ev1.getId() - ev2.getId()).map(ev -> ev.getEvaluation());
		if(projectEvaluation.isEmpty() || labEvaluation.isEmpty()) {
			return Optional.empty();
		}
		Optional<Integer> finalEvaluation = Optional.of((int)Math.round(this.getFinalEvaluation((double)projectEvaluation.get(), (double)labEvaluation.get())));
		this.finalEvaluations.put(this.findStudentFromId(studentId).get(), finalEvaluation.get());
		return finalEvaluation;
	}

	private double getFinalEvaluation(double ExamEvaluation, double ProjectEvaluation) {
		return (ExamEvaluation > ProjectEvaluation 
				? (GREATER_WEIGTH*ExamEvaluation + LOWER_WEIGTH*ProjectEvaluation)/(GREATER_WEIGTH + LOWER_WEIGTH) 
				: (GREATER_WEIGTH*ProjectEvaluation + LOWER_WEIGTH*ExamEvaluation)/(GREATER_WEIGTH + LOWER_WEIGTH));
	}
	
	@Override
	public Map<String, Integer> labExamStudentToEvaluation(int exam) {
		Map<String, Integer> map = new HashMap<>();
		this.labEvaluations.stream()
			.filter(ev -> ev.getId() == exam)
			.forEach(ev -> map.merge(ev.getStudent().getName(), ev.getEvaluation(), (k, v) -> ++v));
		return map;
	}

	@Override
	public Map<String, Integer> allLabExamStudentToFinalEvaluation() {
		Map<String, Integer> map = new HashMap<>();
		this.students.forEach(student -> this.finalEvaluation(student.getId()));
		this.labEvaluations.stream()
		.filter(ev -> this.finalEvaluations.containsKey(ev.getStudent()))
		.forEach(ev -> map.put(ev.getStudent().getName(), this.finalEvaluations.get(ev.getStudent())));
		return map;
	}

	@Override
	public Map<String, Integer> projectEvaluation(String project) {
		Map<String, Integer> map = new HashMap<>();
		this.projectEvaluations.forEach(ev -> map.put(ev.getStudent().getName(), ev.getEvaluation()));
		return map;
	}

}
