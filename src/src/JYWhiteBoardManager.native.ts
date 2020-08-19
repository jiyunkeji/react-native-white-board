import { NativeModules, NativeEventEmitter } from 'react-native';
import type {
  JYWhiteBoardInitOptions,
  Listener,
  JYWhiteBoardJoinRoomOptions,
} from './Types';
import type { JoinRoomEvent } from './JYWhiteBoardEvents';

const { JYWhiteBoardModule } = NativeModules;

const Prefix = JYWhiteBoardModule.JYWB;
const JYWhiteBoardEvent = new NativeEventEmitter(JYWhiteBoardModule);

let whiteBoardManager: JYWhiteBoardManager | undefined;

export default class JYWhiteBoardManager {
  private _listeners = new Map<string, Listener>();

  static instance(): JYWhiteBoardManager {
    if (whiteBoardManager) {
      return whiteBoardManager as JYWhiteBoardManager;
    } else {
      throw new Error('please create RtcEngine first');
    }
  }
  static async init(
    options: JYWhiteBoardInitOptions
  ): Promise<JYWhiteBoardManager> {
    if (whiteBoardManager) return whiteBoardManager;
    await JYWhiteBoardModule.init(options);
    whiteBoardManager = new JYWhiteBoardManager();
    return whiteBoardManager;
  }

  async joinRoom(options: JYWhiteBoardJoinRoomOptions): Promise<void> {
    let res = await JYWhiteBoardModule.JoinRoom(options);
    return res;
  }

  destroy() {
    this.removeAllListener();
    whiteBoardManager = undefined;
  }
  addListener<EventType extends keyof JoinRoomEvent>(
    event: EventType,
    listener: JoinRoomEvent[EventType]
  ) {
    console.log('begin add Listener' + Prefix + event);
    if (!this._listeners.has(Prefix + event)) {
      this._listeners.set(Prefix + event, listener);
      console.log('end add Listener');
      JYWhiteBoardEvent.addListener(Prefix + event, listener);
    }
  }
  removeListener<EventType extends keyof JoinRoomEvent>(
    event: EventType,
    listener: JoinRoomEvent[EventType]
  ) {
    if (this._listeners.has(Prefix + event)) {
      this._listeners.delete(Prefix + event);
      JYWhiteBoardEvent.removeListener(Prefix + event, listener);
    }
  }
  removeAllListener() {
    this._listeners.forEach((value, key) => {
      this._listeners.delete(key);
      JYWhiteBoardEvent.removeListener(key, value);
    });
    this._listeners.clear();
  }
}
