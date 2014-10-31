package secretsanta;

import email.Email;
import email.EmailFactory;
import email.EmailSender;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SecretSantaCoord {

	private EmailSender sender;

	public SecretSantaCoord() {

		sender = new EmailSender();
	}

	public void doDraw() {

		List<Person> buyers = buildParticipants();
		List<Person> receivers = new ArrayList<Person>(buyers);

		boolean startAgain = true;
		int count = 0;

		while(startAgain) {

			startAgain = false;

			for (Person eachBuyer : buyers) {

				Person receiver = findAssignee(eachBuyer, receivers);

				if (receiver == null) {

					// couldn't find a receiver so start again
					startAgain = true;
				}
				else {

					eachBuyer.setAssignee(receiver);
					receivers.remove(receiver);
				}

			}

			count++;
		}

		//printBuyersReceivers(buyers);

		sendEmails(buyers);

		Email email = EmailFactory.secretSantaListEmail(findMe(buyers), buyers, count);
		sender.send(email);
	}

	private void sendEmails(List<Person> buyers) {

		Email email;

		for (Person eachBuyer : buyers) {

			email = EmailFactory.secretSantaEmail(eachBuyer);
			sender.send(email);
		}
	}

	private Person findMe(List<Person> buyers) {

		for (Person eachBuyer : buyers) {

			if (eachBuyer.getName().equals("Jonny")) {

				return eachBuyer;
			}
		}

		return null;
	}

	private void printBuyersReceivers(List<Person> buyers) {

		for (Person eachBuyer : buyers) {

			System.out.println("Name: " + eachBuyer.getName() + ", assigned: " + eachBuyer.getAssignee().getName());
		}
	}

	private Person findAssignee(Person buyer, List<Person> receiversLeft) {

		Collections.shuffle(receiversLeft);

		for (Person eachReceiver : receiversLeft) {

			if (!buyer.getPartner().getName().equals(eachReceiver.getName()) && !buyer.getName().equals(eachReceiver.getName())) {

				return eachReceiver;
			}
		}

		return null;
	}

	public List<Person> buildParticipants() {

		List<Person> participants = new ArrayList<Person>();

		Person tom = new Person("Tom");
		tom.setEmailAddress("tomdavies001@gmail.com");

		Person jude = new Person("Jude");
		jude.setEmailAddress("judedavies05@gmail.com");

		Person steve = new Person("Steve");
		steve.setEmailAddress("steve.davies@vmci.net");

		Person helen = new Person("Helen");
		helen.setEmailAddress("helen@daviescompany.co.uk");

		Person jonny = new Person("Jonny");
		jonny.setEmailAddress("jon.davies@vmci.net");

		Person diane = new Person("Diane");
		diane.setEmailAddress("diprocter@hotmail.com");

		Person alex = new Person("Alex");
		alex.setEmailAddress("al.jones@live.co.uk");

		Person jen = new Person("Jen");
		jen.setEmailAddress("jenkd@live.co.uk");


		tom.setPartner(jude);
		jude.setPartner(tom);
		steve.setPartner(helen);
		helen.setPartner(steve);
		jonny.setPartner(diane);
		diane.setPartner(jonny);
		alex.setPartner(jen);
		jen.setPartner(alex);

		participants.add(tom);
		participants.add(jude);
		participants.add(steve);
		participants.add(helen);
		participants.add(jonny);
		participants.add(diane);
		participants.add(alex);
		participants.add(jen);

		return participants;
	}

}
