package br.com.erudio.data.dto.v1;

import br.com.erudio.jacksonconfig.GenderSerializer;
import br.com.erudio.model.Book;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

//@JsonFilter("PersonFilter") // Foi criada uma classe(ObjectMapperConfig) de filtro onde campos serao ocultados na serializacao
//@JsonPropertyOrder({"id","primeiro_nome","lastName","address", "gender", "senha"}) // Ordenando a ordem dos campos na serialização
@Relation(collectionRelation = "people") // Alterando o nome da lista na representação do findAll na paginacao
public class PersonDTO extends RepresentationModel<PersonDTO> implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    //@JsonProperty("primeiro_nome")// alterando o nome da propriedade na serrialização
    private String firstName;

    private String lastName;

//    @JsonFormat(pattern = "dd/MM/yyyy")
//    private Date birthDay;

    //@JsonIgnore // esse campo nao aparecera na serializacao
    private String address;

    @JsonSerialize(using = GenderSerializer.class) // Formantando o campo com logica na serializacao
    private String gender;

    private Boolean enabled;

    private String profileUrl;
    private String photoUrl;

    @JsonIgnore
    private List<Book> books;

//    @JsonProperty("senha")// alterando o nome da propriedade na serrialização
//    private String sensitiveData;

//    @JsonInclude(JsonInclude.Include.NON_EMPTY) // Se o campo estiver vazio nao sera incluso na serializacao
//    private String phoneNumber;

    public PersonDTO() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    //    public Date getBirthDay() {
//        return birthDay;
//    }
//
//    public void setBirthDay(Date birthDay) {
//        this.birthDay = birthDay;
//    }

//    public String getPhoneNumber() {
//        return phoneNumber;
//    }
//
//    public void setPhoneNumber(String phoneNumber) {
//        this.phoneNumber = phoneNumber;
//    }
//
//    public String getSensitiveData() {
//        return sensitiveData;
//    }
//
//    public void setSensitiveData(String sensitiveData) {
//        this.sensitiveData = sensitiveData;
//    }

    // Esse metodo foi criado pq na geração de PDF o name e lastName estao sendo concatenados
    @JsonIgnore
    public String getName(){
        return (firstName != null ? firstName : "") +
                (lastName != null ? " " + lastName : "");
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PersonDTO personDTO = (PersonDTO) o;
        return Objects.equals(getId(), personDTO.getId()) && Objects.equals(getFirstName(), personDTO.getFirstName()) && Objects.equals(getLastName(), personDTO.getLastName()) && Objects.equals(getAddress(), personDTO.getAddress()) && Objects.equals(getGender(), personDTO.getGender()) && Objects.equals(getEnabled(), personDTO.getEnabled()) && Objects.equals(getProfileUrl(), personDTO.getProfileUrl()) && Objects.equals(getPhotoUrl(), personDTO.getPhotoUrl()) && Objects.equals(getBooks(), personDTO.getBooks());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getId(), getFirstName(), getLastName(), getAddress(), getGender(), getEnabled(), getProfileUrl(), getPhotoUrl(), getBooks());
    }
}
