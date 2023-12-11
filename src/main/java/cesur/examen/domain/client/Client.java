package cesur.examen.domain.client;

import cesur.examen.domain.car.Car;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * EXAMEN DE ACCESO A DATOS
 * Diciembre 2023
 *
 * Nombre del alumno:Victor Jesus Fernandez Noguer
 * Fecha: 11/12/2023
 */


@NoArgsConstructor
@AllArgsConstructor

@Data
@Entity
@Table(name = "cliente")
public class Client implements Serializable {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "nombre")
    private String name;

    private String email;

    @OneToMany (mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Car> cars = new ArrayList<Car>(0);

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", cars=" + cars +
                '}';
    }

    /**
     * Utility to add a car to a client.
     * This allows to maintain bidirectional consistency over the relationship,
     * providing client information to the car added.
     *
     * @param c Car to be added to the client.
     */
    public void addCar(Car c){
        c.setClient(this);
        cars.add(c);
    }

}