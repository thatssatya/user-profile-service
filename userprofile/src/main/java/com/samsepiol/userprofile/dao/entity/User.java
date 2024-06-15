package com.samsepiol.userprofile.dao.entity;

import com.samsepiol.userprofile.model.DOB;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Period;

@Getter
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Builder
public class User {
    @JsonProperty("_id")
    private final String userId;
    private final String name;
    private final String nationalId;
    private final DOB dob;

    @JsonIgnore
    public int getAge() {
        return Period
                .between(LocalDate.of(dob.getYear(), dob.getMonth(), dob.getMonth()), LocalDate.now())
                .getYears();
    }
}
