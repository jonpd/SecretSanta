package email;

import java.util.List;
import secretsanta.Person;

public final class EmailFactory {

    private EmailFactory() {
    }

    private static Email createEmail(Person person, String body, String subject) {

        Email email = new Email();
        email.addRecipient(person.getEmailAddress());
        email.setBody(body);
        email.setSubject(subject);
        return email;
    }

    public static Email secretSantaEmail(Person person) {

        String body = "Your secret santa is: " + person.getAssignee().getName() + " , ssh!"

				+ "\n\nTo request a re-draw please send a cheque for £57.35 made payable to Jonathan Davies... (warning: if you reply to this email there's a chance I will the person you've been assigned within the email thread)";

		String subject = "Your secret santa allocation";

		return createEmail(person, body, subject);
    }

	public static Email secretSantaListEmail(Person person, List<Person> buyers, int count) {

        String body = "Allocation list (attempts: " + count + ")\n" + getAllocationList(buyers);

		String subject = "The secret santa allocation list";

		return createEmail(person, body, subject);
    }

	private static String getAllocationList(List<Person> buyers) {

		StringBuilder sb = new StringBuilder();

		for (Person eachBuyer : buyers) {

			sb.append("\nName: " + eachBuyer.getName() + ", assigned: " + eachBuyer.getAssignee().getName());
		}

		return sb.toString();
	}
}
