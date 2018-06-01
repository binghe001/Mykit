package io.mykit.lock.redis.service.impl;

import java.util.HashMap;
import java.util.Map;

import io.mykit.lock.redis.entity.Goods;
import io.mykit.lock.redis.service.SeckillInterface;

/**
 * 秒杀系统实现
 * @author liuyazhuang
 *
 */
public class SecKillImpl implements SeckillInterface{
	public static Map<Long, Long> inventory ;
	
	public static Map<Long, Goods> goodsMap;
	
	static{
		inventory = new HashMap<>();
		inventory.put(10000001L, 10000l);
		inventory.put(10000002L, 10000l);
		
		goodsMap = new HashMap<Long, Goods>();
		goodsMap.put(1L, new Goods(1L, 1000));
		goodsMap.put(2L, new Goods(2L, 2000));
	}
	
	@Override
	public void secKill(String arg1, Long arg2) {
		//最简单的秒杀，这里仅作为demo示例
		reduceInventory(arg2);
	}
	//模拟秒杀操作，姑且认为一个秒杀就是将库存减一，实际情景要复杂的多
	public Long reduceInventory(Long commodityId){
		inventory.put(commodityId,inventory.get(commodityId) - 1);
		return inventory.get(commodityId);
	}
	@Override
	public void secKill(Goods goods) {
		Goods g = goodsMap.get(goods.getId());
		g.setCount(g.getCount() - 1);
		goodsMap.put(goods.getId(), g);
	}

}
