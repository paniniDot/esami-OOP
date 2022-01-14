package e1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ExamsManagementImpl implements ExamsManagement {

	private final List<Student> students = new ArrayList<>();
	private final List<Exam> exams = new ArrayList<>();
	private final List<Registration> registrations = new ArrayList<>();
	private Optional<Exam> currentExamStarted = Optional.empty();
	private Optional<Exam> lastExamCompleted = Optional.empty();
	
	@Override
	public void createStudent(int studentId, String name) {
		this.students.add(new Student(name, studentId));
	}
	
	@Override
	public void createExam(String examName, int incrementalId) {
		this.exams.add(new Exam(examName, incrementalId));
	}
	
	private <T> T findFromList(List<? extends T> list, Predicate<? super T> predicate) {
		return list.stream().filter(t -> predicate.test(t)).findAny().get();
	}

	private Exam findExamFromName(String examName) {
		return this.findFromList(this.exams, exam -> exam.getName().equals(examName));
	}
	
	private Student findStudentFromId(int studentId) {
		return this.findFromList(this.students, student -> student.getId()==studentId);
	}
	
	@Override
	public void registerStudent(String examName, int studentId) {
		this.registrations.add(new Registration(this.findExamFromName(examName), this.findStudentFromId(studentId)));
	}

	@Override
	public void examStarted(String examName) {
		if(this.currentExamStarted.isPresent() || this.lastExamCompleted.isPresent() && this.lastExamCompleted.get().getId() > this.findExamFromName(examName).getId()){
			throw new IllegalStateException();
		}
		this.currentExamStarted = Optional.of(this.findExamFromName(examName));
	}

	@Override
	public void registerEvaluation(int studentId, int evaluation) {
		if(this.currentExamStarted.isEmpty()) {
			throw new IllegalStateException();
		}
		this.registrations.stream()
			.filter(reg -> reg.getExam().getName().equals(this.currentExamStarted.get().getName()) && reg.getStudent().getId() == studentId)
			.findAny()
			.get()
			.setEvaluation(evaluation);
	}

	@Override
	public void examFinished() {
		if(this.currentExamStarted.isEmpty()) {
			throw new IllegalStateException();
		}
		this.lastExamCompleted = Optional.of(this.currentExamStarted.get());
		this.currentExamStarted = Optional.empty();
	}

	@Override
	public Set<Integer> examList(String examName) {
		return this.registrations.stream()
				.filter(reg -> reg.getExam().getName().equals(examName))
				.map(reg -> reg.getStudent().getId())
				.collect(Collectors.toSet());
	}

	private Exam findExamFromId(int examId) {
		return this.findFromList(this.exams, exam -> exam.getId() == examId);
	}
	
	private Registration findRegFromExamAndStudent(Exam exam, Student student) {
		return this.findFromList(this.registrations, reg -> reg.getExam().equals(exam) && reg.getStudent().equals(student));
	}
	
	@Override
	public Optional<Integer> lastEvaluation(int studentId) {
		int id = this.registrations.stream().filter(reg -> reg.getStudent().getId() == studentId).map(reg -> reg.getExam().getId()).max(Integer::compareTo).get();
		return this.findRegFromExamAndStudent(this.findExamFromId(id), this.findStudentFromId(studentId)).getEvaluation();
	}

	@Override
	public Map<String, Integer> examStudentToEvaluation(String examName) {
		Map<String, Integer> map = new HashMap<>();	
				this.registrations.stream()
				.filter(reg -> reg.getExam().getName().equals(examName) && reg.getEvaluation().isPresent())
				.forEach(reg -> map.put(reg.getStudent().getName(), reg.getEvaluation().get()));
		return map;
	}

	@Override
	public Map<Integer, Integer> examEvaluationToCount(String examName) {
		Map<Integer, Integer> map = new HashMap<>();
		this.examStudentToEvaluation(examName)
			.entrySet().stream()
			.filter(entry -> entry.getValue() > 0)
			.forEach(entry -> map.merge(entry.getValue(), 1, (k, v) -> ++v));
		return map;
	}
	
}
