insert into userAccount(userId, userName, password) values(3, 'demo', '{pbkdf2}1dd84f42a7a9a173f8f806d736d34939bed6a36e2948e8bfe88801ee5e6e61b815efc389d03165a4');
insert into bingoRoom(bingoRoomId, roomName, started, finished, createUserId, createdAt, updatedAt) values(10, 'test1', false, false, 3, '2020-01-20 21:07:22', '2020-01-20 21:07:22');
insert into bingoRoom(bingoRoomId, roomName, started, finished, createUserId, createdAt, updatedAt) values(11, 'test2', true, false, 3, '2020-01-20 21:07:22', '2020-01-20 21:07:22');
insert into bingoResult(bingoId, bingoValue, createdAt, bingoRoomId) values(11, 11, '2020-01-20 21:07:22', 10);
insert into bingoResult(bingoId, bingoValue, createdAt, bingoRoomId) values(12, 12, '2020-01-20 21:07:22', 10);
insert into bingoResult(bingoId, bingoValue, createdAt, bingoRoomId) values(13, 13, '2020-01-20 21:07:22', 11);
insert into bingoResult(bingoId, bingoValue, createdAt, bingoRoomId) values(14, 14, '2020-01-20 21:07:22', 11);