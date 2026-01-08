package com.blackcat.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersOverViewVO {
    private Integer currentMonthUsers;
    private Integer lastMonthUsers;
}
