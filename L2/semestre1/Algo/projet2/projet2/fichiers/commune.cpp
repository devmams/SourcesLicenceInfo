#include <sstream> // stringstream
#include "commune.hpp"

/* inspiré de http://stackoverflow.com/questions/236129/split-a-string-in-c/236803#236803 */
void get_data( const std::string &s, Commune &commune )
{
  std::stringstream stream;
  stream.str( s );
  std::string item;
  std::getline( stream, item, ':' );
  commune.nom = item;
  std::getline( stream, item, ':' );
  commune.priorite = stoi( item );
}
