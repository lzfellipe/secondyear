library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity Zero_fill_tb is
--  Port ( );
end Zero_fill_tb;

architecture Behavioral of Zero_fill_tb is

    -- declare component to test
    component Zero_fill is
        Port ( 
            SB : in std_logic_vector(2 downto 0);
            zeroFill : out std_logic_vector(15 downto 0)
        );
    end component;
    
    -- signals for tests (initialise to 0)
        
    --inputs    
    signal SB : std_logic_vector(2 downto 0):= "000";
    
    --outputs
    signal zeroFill : std_logic_vector(15 downto 0) := x"0000";
        
begin

    -- instantiate component for test, connect ports to internal signals
    UUT: Zero_fill
    Port Map(
        SB => SB,
        zeroFill => zeroFill
    );
    
simulation_process :process
begin
        
        --Test non zero value
        SB <= "111";
        wait for 10ns;
        
        --Test another value
        SB <= "101";
        wait for 10ns;
        
     wait;
     end process;
    
end Behavioral;
