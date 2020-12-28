package com.alina.singstreet.data;

import com.alina.singstreet.R;

public class DataFactory {
    static DataFactory factory;
    int[] icons = {R.drawable.icon1, R.drawable.icon2, R.drawable.icon3, R.drawable.icon4, R.drawable.icon5, R.drawable.icon6, R.drawable.icon7};
    String[] nicknames = {"Johnny", "黑皮", "疯曲大佐", "Alina", "木桥CD", "不会唱歌的DJ"};
    String[] phoneNumbers = {"111000000", "122000000", "133000000", "144000000", "155000000", "166000000"};
    String[] password = {"000000", "000000", "000000", "000000", "000000", "000000"};
    String description = "莎士比亚在不经意间这样说过，本来无望的事，大胆尝试，往往能成功。这句话语虽然很短, 但令我浮想联翩. " +
            "笛卡儿曾经说过，阅读一切好书如同和过去最杰出的人谈话。这句话看似简单，但其中的阴郁不禁让人深思. 经过上述讨论， 一般来讲，我们都必须务必慎重的考虑考虑。 " +
            "我认为， 要想清楚，问题，到底是一种怎么样的存在。 我们一般认为，抓住了问题的关键，其他一切则会迎刃而解。 所谓问题，关键是问题需要如何写。";

    DataFactory() {

    }

    public static DataFactory getInstance() {
        if (factory == null) {
            factory = new DataFactory();
        }
        return factory;
    }
}
