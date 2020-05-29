package entities;

public class Master {
    private int id;
    private Speciality speciality;
    private String fullName;

    public Master( Speciality speciality, String fullName) {
        this.speciality = speciality;
        this.fullName = fullName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Speciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "entities.Master{" +
                "id=" + id +
                ", speciality=" + speciality +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}
