package e1;

import java.util.Objects;

public class ProjectEvaluation {
	
	private final String name;
	private final Student student;
	private final int evaluation;
	
	public ProjectEvaluation(String project, Student student, int evaluation) {
		this.name = project;
		this.student = student;
		this.evaluation = evaluation;
	}

	public String getName() {
		return name;
	}

	public Student getStudent() {
		return student;
	}

	public int getEvaluation() {
		return this.evaluation;
	}

	@Override
	public int hashCode() {
		return Objects.hash(evaluation, name, student);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProjectEvaluation other = (ProjectEvaluation) obj;
		return evaluation == other.evaluation && Objects.equals(name, other.name)
				&& Objects.equals(student, other.student);
	}

	@Override
	public String toString() {
		return "ProjectEvaluation [name=" + name + ", student=" + student + ", evaluation=" + evaluation + "]";
	}
	
}
