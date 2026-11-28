package org.mycode.redisseckilldemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeckillOrder implements Serializable {

    private static final long serialVersionUTD = 1L;

    private Long userId;
    private Long productId;
    private Long createTime;
}
