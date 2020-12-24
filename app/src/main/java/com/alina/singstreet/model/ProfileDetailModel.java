package com.alina.singstreet.model;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class ProfileDetailModel {
    @Embedded
    public ProfileModel profileModel;

    @Relation(
            parentColumn = "userUID",
            entityColumn = "userUID"
    )

    public List<SingCardModel> list;

    @Override
    public String toString() {
        return "ProfileDetailModel{" +
                "profileModel=" + profileModel +
                ", list=" + list +
                '}';
    }
}
