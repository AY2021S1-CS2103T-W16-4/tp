package atas.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import atas.model.AddressBook;
import atas.model.ReadOnlyAddressBook;
import atas.model.person.Email;
import atas.model.person.Matriculation;
import atas.model.person.Name;
import atas.model.person.Person;
import atas.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Matriculation("A1234567X"), new Email("alexyeoh@u.nus.edu"),
                getTagSet("friends")),
            new Person(new Name("Bernice Yu"), new Matriculation("A7654321X"), new Email("berniceyu@u.nus.edu"),
                getTagSet("colleagues", "friends")),
            new Person(new Name("Charlotte Oliveiro"), new Matriculation("A0962345D"), new Email("charlotte@u.nus.edu"),
                getTagSet("neighbours"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
