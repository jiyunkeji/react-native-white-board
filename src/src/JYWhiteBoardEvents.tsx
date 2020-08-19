import type { RoomState } from './Types';

export type RoomStateChanged = (warn: RoomState) => void;

export interface JoinRoomEvent {
  onRoomStateChanged: RoomStateChanged;
}
