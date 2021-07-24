package com.stackroute.graphcommandservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArrayTreeList {
    List<ArrayTree> concepts;
}
