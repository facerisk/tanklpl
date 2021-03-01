package com.lpl.tank;

import java.io.Serializable;

/**
 * @Classname FireStrategy
 * @Description 子弹发射策略
 * @Date 2021/1/26 20:09
 * @Created by lplmbp
 */
public interface FireStrategy extends Serializable {
    void fire(Tank t);
}
