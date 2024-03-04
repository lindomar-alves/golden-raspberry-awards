package br.com.textoit.goldenraspberryawards.dto;

import java.util.List;

public record GoldenRaspberryAwardsDTO(List<ProducerStats> min, List<ProducerStats> max) {}
