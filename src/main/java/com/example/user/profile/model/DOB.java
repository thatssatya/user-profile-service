package com.example.user.profile.model;

import com.example.user.profile.util.CommonUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DOB {
    private @NotBlank Integer day;
    private @NotBlank Integer month;
    private @NotBlank Integer year;

    public static DOB from(@NotBlank String dd, @NotBlank String mm, @NotBlank String yyyy) {
        return new DOB(Integer.parseInt(dd), Integer.parseInt(mm), Integer.parseInt(yyyy));
    }

    public static DOB from(@NotBlank Integer dd, @NotBlank Integer mm, @NotBlank Integer yyyy) {
        return new DOB(dd, mm, yyyy);
    }

    @JsonIgnore
    public boolean isValid() {
        return CommonUtils.paramsNotEmpty(day, month, year);
    }

    @Override
    public String toString() {
        return String.format("%s/%s/%s", day, month, year);
    }
}
