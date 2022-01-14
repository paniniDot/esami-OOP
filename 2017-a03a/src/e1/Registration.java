package e1;

import java.util.Objects;
import java.util.Optional;

public class Registration {
	
	private final Exam exam;
	private final Student student;
	private Optional<Integer> evaluation;
	
	public Registration(final Exam exam, final Student student) {
		this.exam = exam;
		this.student = student;
		this.evaluation = Optional.empty();
	}

	public Optional<Integer> getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(int evaluation) {
		this.evaluation = Optional.of(evaluation);
	}

	public Exam getExam() {
		return exam;
	}

	public Student getStudent() {
		return student;
	}

	@Override
	public String toString() {
		return "Registration [exam=" + exam + ", student=" + student + ", evaluation=" + evaluation + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(evaluation, exam, student);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Registration other = (Registration) obj;
		return Objects.equals(evaluation, other.evaluation) && Objects.equals(exam, other.exam)
				&& Objects.equals(student, other.student);
	}
	
	
	
}
