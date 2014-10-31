package secretsanta;

public class Person {

	private String name;
	private Person partner;
	private Person assignee;
	private String emailAddress;

	public Person(String aName) {

		name = aName;
	}

	public Person(String aName, Person aPartner) {

		name = aName;
		partner = aPartner;
	}

	public Person getPartner() {

		return partner;
	}

	public void setPartner(Person aPerson) {

		partner = aPerson;
	}

	public String getName() {

		return name;
	}

	public void setAssignee(Person aPerson) {

		assignee = aPerson;
	}

	public Person getAssignee() {

		return assignee;
	}

	public void setEmailAddress(String email) {

		emailAddress = email;
	}

	public String getEmailAddress() {

		return emailAddress;
	}
}
