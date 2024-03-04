package br.com.textoit.goldenraspberryawards.dto;

public record ProducerStats(String producer, int interval, int previousWin, int followingWin) {
}
