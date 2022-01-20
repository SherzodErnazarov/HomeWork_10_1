package uz.pdp.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int number;
    private int floor;
    private int size;
    @ManyToOne
    private Hotel hotel;
}
