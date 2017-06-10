package com.magic.crius.po;

/**
 *  tethys 中的gameInfo
 */
public class GameInfo {

    private Integer id;

    private String gameId;

    private String gameName;

    private String gameType;

    private String gameTypeName;    //游戏类型名称

    private String gameFactoryType; //游戏组类型

    private String gameFactoryTypeName; //游戏组类型名称

    private String gameAbstractType;    //游戏类别

    private String gameAbstractTypeName;    //游戏类别名称

    private Long createTime;

    private Long updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public String getGameTypeName() {
        return gameTypeName;
    }

    public void setGameTypeName(String gameTypeName) {
        this.gameTypeName = gameTypeName;
    }

    public String getGameFactoryType() {
        return gameFactoryType;
    }

    public void setGameFactoryType(String gameFactoryType) {
        this.gameFactoryType = gameFactoryType;
    }

    public String getGameFactoryTypeName() {
        return gameFactoryTypeName;
    }

    public void setGameFactoryTypeName(String gameFactoryTypeName) {
        this.gameFactoryTypeName = gameFactoryTypeName;
    }

    public String getGameAbstractType() {
        return gameAbstractType;
    }

    public void setGameAbstractType(String gameAbstractType) {
        this.gameAbstractType = gameAbstractType;
    }

    public String getGameAbstractTypeName() {
        return gameAbstractTypeName;
    }

    public void setGameAbstractTypeName(String gameAbstractTypeName) {
        this.gameAbstractTypeName = gameAbstractTypeName;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }
}