package main.utils;

public class ContractPointsCalculator {

    public static int calculatePoints(int height, String color, int doubleValue, boolean vulnerable, int tricks) {
        if (height == 0) {
            return 0;
        }

        if (tricks < height + 6) {
            return calculateUndertrickPoints(height, tricks, doubleValue, vulnerable);
        }

        if (color.equals("NT")) {
            return calculateNoTrumpPoints(height, doubleValue, vulnerable, tricks);
        } else if (color.equals("S") || color.equals("H")) {
            return calculateMajorPoints(height, doubleValue, vulnerable, tricks);
        } else {
            return calculateMinorPoints(height, doubleValue, vulnerable, tricks);
        }
    }

    private static int calculateNoTrumpPoints(int height, int doubleValue, boolean vulnerable, int tricks) {
        return calculatePoints(40, 30, doubleValue, height, tricks, vulnerable, 3);

    }

    private static int calculateMajorPoints(int height, int doubleValue, boolean vulnerable, int tricks) {
        return calculatePoints(30, 30, doubleValue, height, tricks, vulnerable, 4);
    }

    private static int calculateMinorPoints(int height, int doubleValue, boolean vulnerable, int tricks) {
        return calculatePoints(20, 20, doubleValue, height, tricks, vulnerable, 5);
    }


    private static int calculatePoints(int firstTrick, int subsequentTricks, int doubleValue, int height, int tricks, boolean vulnerable, int bonusPlay) {
        int base = 0;
        int trickPoints = 0;
        int overtricks = tricks - (height + 6);
        int doubleBonus = 0;

        if (vulnerable) {
            if (height < bonusPlay) {
                base += 50;
            } else {
                base += 500;
                if (height == 6) {
                    base += 1000;
                }
                if (height == 7) {
                    base += 1500;
                }
            }
            if (overtricks > 0) {
                if (doubleValue == 0) {
                    trickPoints += firstTrick * overtricks;
                } else if (doubleValue == 1) {
                    trickPoints += 200 * overtricks;
                } else if (doubleValue == 2) {
                    trickPoints += 400 * overtricks;
                }
            }
        } else {
            if (height < bonusPlay) {
                base += 50;
            } else {
                base += 300;
                if (height == 6) {
                    base += 500;
                }
                if (height == 7) {
                    base += 1000;
                }
            }
            if (overtricks > 0) {
                if (doubleValue == 0) {
                    trickPoints += firstTrick * overtricks;
                } else if (doubleValue == 1) {
                    trickPoints += 100 * overtricks;
                } else if (doubleValue == 2) {
                    trickPoints += 200 * overtricks;
                }
            }
        }

        if (doubleValue == 0) {
            trickPoints += firstTrick;
            if (height > 1) {
                trickPoints += subsequentTricks * (height - 1);
            }
        } else if (doubleValue == 1) {
            trickPoints += firstTrick * 2;
            if (height > 1) {
                trickPoints += subsequentTricks * 2 * (height - 1);
            }
        } else if (doubleValue == 2) {
            trickPoints += firstTrick * 4;
            if (height > 1) {
                trickPoints += subsequentTricks * 4 * (height - 1);
            }
        }

        if (doubleValue == 1) {
            doubleBonus = 50;
        } else if (doubleValue == 2) {
            doubleBonus = 100;
        }

        return base + trickPoints + doubleBonus;
    }

    private static int calculateUndertrickPoints(int height, int tricks, int doubleValue, boolean vulnerable) {
        int tricksToScore = height + 6;
        int result = 0;
        int undertricks = tricksToScore - tricks;
        // bez kontry
        if (doubleValue == 0) {
            result = 50 * undertricks;
            if (vulnerable) {
                return result * 2;
            }
            return result;
        }
        // z kontra
        if (doubleValue == 1) {
            if (vulnerable) {
                if (undertricks == 1) {
                    result = 200;
                } else {
                    result = 200 + (undertricks - 1) * 300;
                }
            } else {
                if (undertricks == 1) {
                    result = 100;
                } else if (undertricks == 2) {
                    result = 300;
                } else {
                    result = 300 + (undertricks - 2) * 300;
                }
            }
        }
        // z rekontra
        if (doubleValue == 2) {
            if (vulnerable) {
                if (undertricks == 1) {
                    result = 400;
                } else {
                    result = 400 + (undertricks - 1) * 600;
                }
            } else {
                if (undertricks == 1) {
                    result = 200;
                } else if (undertricks == 2) {
                    result = 600;
                } else {
                    result = 600 + (undertricks - 2) * 600;
                }
            }
        }
        return -1 * result;
    }
}
