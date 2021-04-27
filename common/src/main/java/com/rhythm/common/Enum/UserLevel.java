package com.rhythm.common.Enum;

public enum UserLevel {
    ADMIN(1), MANAGER(2), WAITER(3);

    int level;

    UserLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}
