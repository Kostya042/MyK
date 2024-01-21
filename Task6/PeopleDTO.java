package Task6;

import lombok.*;

@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class PeopleDTO   {
    private int id;
    private int age;
    private double salary;
    private String passport;
    private String address;
    private String dataOfBirthday;
    private String dataTimeCreate;
    private String timeToLunch;
    private String letter;

}
