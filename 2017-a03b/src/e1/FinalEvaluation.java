package e1;

import java.util.Objects;

public class FinalEvaluation {
	
	private final static int GREATER_WEIGTH = 60;
	private final static int LOWER_WEIGTH = 40;
	
	private final int evaluation;
	private final Student student;
	
	
	
	public FinalEvaluation(double ExamEvaluation, double ProjectEvaluation, Student student) {
		this.evaluation = (int)Math.round(this.getFinalEvaluation(ExamEvaluation, ProjectEvaluation));
		this.student = student;
	}
	
	private double getFinalEvaluation(double ExamEvaluation, double ProjectEvaluation) {
		return ExamEvaluation > ProjectEvaluation 
				? (GREATER_WEIGTH*ExamEvaluation + LOWER_WEIGTH*ProjectEvaluation)/(GREATER_WEIGTH + LOWER_WEIGTH) 
				: (GREATER_WEIGTH*ProjectEvaluation + LOWER_WEIGTH*ExamEvaluation)/(GREATER_WEIGTH + LOWER_WEIGTH);
	}
	
	public int getEvaluation() {
		return evaluation;
	}
	public Student getStudent() {
		return student;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(evaluation, student);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FinalEvaluation other = (FinalEvaluation) obj;
		return evaluation == other.evaluation && Objects.equals(student, other.student);
	}

	@Override
	public String toString() {
		return "FinalEvaluation [evaluation=" + evaluation + ", student=" + student + "]";
	}
	
	
	
}
