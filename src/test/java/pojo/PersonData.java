package pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.json.JSONPropertyIgnore;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonData {
    private String name;
    private String height;

    public PersonData(){}
    public PersonData(String name, String height){
        this.name = name;
        this.height = height;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setHeight(String height) {
        this.height = height;
    }
    public String getName() {
        return name;
    }
    public String getHeight() {
        return height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonData that = (PersonData) o;
        return name.equals(that.name) && height.equals(that.height);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, height);
    }
}
