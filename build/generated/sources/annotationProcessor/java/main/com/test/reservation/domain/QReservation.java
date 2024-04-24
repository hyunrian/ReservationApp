package com.test.reservation.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QReservation is a Querydsl query type for Reservation
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReservation extends EntityPathBase<Reservation> {

    private static final long serialVersionUID = -1452798509L;

    public static final QReservation reservation = new QReservation("reservation");

    public final NumberPath<Integer> attendee = createNumber("attendee", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> resEnd = createDateTime("resEnd", java.time.LocalDateTime.class);

    public final StringPath resName = createString("resName");

    public final DateTimePath<java.time.LocalDateTime> resStart = createDateTime("resStart", java.time.LocalDateTime.class);

    public final StringPath title = createString("title");

    public QReservation(String variable) {
        super(Reservation.class, forVariable(variable));
    }

    public QReservation(Path<? extends Reservation> path) {
        super(path.getType(), path.getMetadata());
    }

    public QReservation(PathMetadata metadata) {
        super(Reservation.class, metadata);
    }

}

