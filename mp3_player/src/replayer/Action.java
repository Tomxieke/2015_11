package replayer;

public interface Action {
	public static final String PLAY_MUSIC = "play_music";
	public static final String LAST_MUSIC = "last_music";
	public static final String NAXT_MUSIC = "naxt_music";
	public static final String PAUSE_MUSIC = "pause_music";
	public static final String PLAY_STATE_PLAYINT  = "play_state_playing";
	public static final String PLAY_STATE_PAUSE  = "play_state_pause";
	public static final String PLAY_TIME  = "play_time";
	public static final String MUSIC_TOTAL_TIME  = "music_total_time";  //音乐播放总时间
	public static final String MUSIC_NOW_TIME  = "music_now_time"; //音乐播放当前时间
	public static final String USER_PROGRESS  = "user_progress"; //用户拖动seekbar的值
	public static final String USER_PUSH_SEEKBAR  = "user_push_seekbar"; //是否是用户在拖动
	public static final String SEND_NOTIFICATION  = "send_notification"; //退出activity时发送通知
	public static final String MUSIC_NAME = "music_name";//正在播放音乐的
}
