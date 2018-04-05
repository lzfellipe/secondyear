library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity mux8_1bit_tb is
--  Port ( );
end mux8_1bit_tb;

architecture Behavioral of mux8_1bit_tb is
    -- declare component to test
    component mux8_1bit is
        Port ( 
		    In0 : in std_logic; 
		    In1 : in std_logic; 
		    In2 : in std_logic; 
		    In3 : in std_logic; 
		    In4 : in std_logic; 
		    In5 : in std_logic; 
		    In6 : in std_logic; 
		    In7 : in std_logic;
			S0 : in std_logic;
			S1 : in std_logic;
			S2 : in std_logic;
			Z : out std_logic
		);
    end component;
    
    -- signals for tests (initialise to 0)
        
    --inputs    
    signal In0 : std_logic := '0';
    signal In1 : std_logic := '0';
    signal In2 : std_logic := '0';
    signal In3 : std_logic := '0';
    signal In4 : std_logic := '0';
    signal In5 : std_logic := '0';
    signal In6 : std_logic := '0';
    signal In7 : std_logic := '0';    
    signal S0: std_logic := '0';
    signal S1: std_logic := '0';
    signal S2: std_logic := '0';
    
    --outputs    
    signal Z : std_logic := '0';
        
begin

    -- instantiate component for test, connect ports to internal signals
    UUT: mux8_1bit
    Port Map(
        In0 => In0,
        In1 => In1,
        In2 => In2,
        In3 => In3,
        In4 => In4,
        In5 => In5,
        In6 => In6,
        In7 => In7,
        S0 => S0,
        S1 => S1,
        S2 => S2,
        Z => Z
    );
    
simulation_process :process
begin
		--Test Select In0
        In0 <= '1';
        S0 <= '0';
        S1 <= '0';
        S2 <= '0';
        wait for 5ns;

		--Test Select In1
        In0 <= '0';
        In1 <= '1';
        S0 <= '0';
        S1 <= '0';
        S2 <= '1';
        wait for 5ns; 
        
 		--Test Select In2
        In1 <= '0';
        In2 <= '1';
        S0 <= '0';
        S1 <= '1';
        S2 <= '0';
        wait for 5ns;
 
 		--Test Select In3
       In2 <= '0';
       In3 <= '1';
       S0 <= '0';
       S1 <= '1';
       S2 <= '1';
       wait for 5ns;   
       
 	  --Test Select In4
      In3 <= '0';
      In4 <= '1';
      S0 <= '1';
      S1 <= '0';
      S2 <= '0';
      wait for 5ns;            

 	  --Test Select In5
      In4 <= '0';
      In5 <= '1';
      S0 <= '1';
      S1 <= '0';
      S2 <= '1';
      wait for 5ns; 

 	  --Test Select In6
      In5 <= '0';
      In6 <= '1';
      S0 <= '1';
      S1 <= '1';
      S2 <= '0';
      wait for 5ns;

 	  --Test Select In7
      In6 <= '0';
      In7 <= '1';
      S0 <= '1';
      S1 <= '1';
      S2 <= '1';
      wait for 5ns;

     end process;
    
end Behavioral;