package e1;

import java.util.Objects;

public class LabEvaluation {
	
	private final int id;
	private final Student student;
	private final int evaluation;
	
	
	public LabEvaluation(final int examId, final Student student, final int evaluation) {
		this.id = examId;
		this.student = student;
		this.evaluation = evaluation;
	}

	public int getId() {
		return id;
	}

	public Student getStudent() {
		return student;
	}

	public int getEvaluation() {
		return evaluation;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(evaluation, id, student);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LabEvaluation other = (LabEvaluation) obj;
		return evaluation == other.evaluation && id == other.id && Objects.equals(student, other.student);
	}

	@Override
	public String toString() {
		return "LabEvaluation [id=" + id + ", student=" + student + ", evaluation=" + evaluation + "]";
	}

	
	
}