package fr.chalodss.utils;

public final class Constants {
    
    private Constants() {
        
    }

    private static final String[] MAN = {
        """    
            ------
            |    |
            |
            |
            |
            |
            |
            |
            --------""",
            """
                ------
                |    |
                |    0
                |
                |
                |
                |
                |
                --------""",
                """ 
                    ------
                    |    |
                    |    0
                    |    |
                    |    |
                    |
                    |
                    |
                    --------""",
                    """
                        ------
                        |    |
                        |    0
                        | -- |
                        |    |
                        |
                        |
                        |
                        --------""",
                        """
                            ------
                            |    |
                            |    0
                            | -- | --
                            |    |
                            |
                            |
                            |
                            --------""",
                            """
                                ------
                                |    |
                                |    0
                                | -- | --
                                |    |
                                |   |
                                |   |
                                |
                                --------""",
                                """
                                    ------
                                    |    |
                                    |    0
                                    | -- | --
                                    |    |
                                    |   | |
                                    |   | |
                                    |
                                    --------"""
    };

    public static String[] getMan() {
      return MAN;
    }

}
