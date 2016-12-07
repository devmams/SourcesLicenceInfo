#pragma once

#include <string>

struct Commune
{
  std::string nom;
  unsigned int priorite; 
};

void get_data( const std::string &s, Commune &commune );
